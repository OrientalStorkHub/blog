package com.orientalstorkhub.blog.gatewayservice.filters;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.orientalstorkhub.blog.common.constants.ErrorCode;
import com.orientalstorkhub.blog.common.constants.RedisKeyPrefix;
import com.orientalstorkhub.blog.common.exception.BlogBaseException;
import com.orientalstorkhub.blog.common.responses.BaseResponse;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import reactor.core.publisher.Mono;

import org.springframework.util.StringUtils;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    //过滤白名单
    private static final String[] WHITE_LIST = {
            "/auth/login",
            "/auth/register",
            "/doc",
            "/v2/api-docs",
            "/v3/api-docs",
            "/**/v3/api-docs"

    };
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Value("${jwt.refresh-threshold}")
    private long refreshThreshold;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 判断是否在白名单中
        if (isWhiteList(exchange.getRequest().getURI().getPath())) {
            return chain.filter(exchange);
        }
        // 从请求头中获取access-token和refresh-token
        String accessToken = getTokenFromRequest(exchange.getRequest());
        String refreshToken = getRefreshTokenFromRequest(exchange.getRequest());
        
        if (accessToken == null || accessToken.isEmpty()) {
            return   handleUnauthorized(exchange);
        }       

        //验证access-token是否在黑名单
        if (isTokenBlacklist(accessToken)) {
            return handleUnauthorized(exchange);
        }
        // 验证JWT
        if (JWTUtil.verify(accessToken, secretKey.getBytes()) && !isTokenExpired(accessToken)) {
            // 检查token是否快过期
            if (isTokenNearExpiration(accessToken)) {
                // 访问令牌即将过期，尝试使用刷新令牌
                if (StringUtils.hasText(refreshToken) && JWTUtil.verify(refreshToken, secretKey.getBytes()) && !isTokenExpired(refreshToken)) {
                    String[] newTokens = refreshTokens(refreshToken);
                    exchange.getResponse().getHeaders().add("New-Access-Token", newTokens[0]);
                    exchange.getResponse().getHeaders().add("New-Refresh-Token", newTokens[1]);
                }
            }
            return chain.filter(exchange);
        } else if (StringUtils.hasText(refreshToken) && JWTUtil.verify(refreshToken, secretKey.getBytes()) && !isTokenExpired(refreshToken)) {
            // 访问令牌已过期，但刷新令牌有效
            String[] newTokens = refreshTokens(refreshToken);
            exchange.getResponse().getHeaders().add("New-Access-Token", newTokens[0]);
            exchange.getResponse().getHeaders().add("New-Refresh-Token", newTokens[1]);
            return chain.filter(exchange);
        }else{
            // 返回未授权错误
            return handleUnauthorized(exchange);
        }
    }

    //判断token是否在黑名单
    private boolean isTokenBlacklist(String accessToken) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(RedisKeyPrefix.BLACKLIST.getPrefix() + accessToken));
    }

    //判断路径是否在白名单中
    private boolean isWhiteList(String path) {
        for (String whitePath : WHITE_LIST) {
            if (path.contains(whitePath)) {
                return true;
            }
        }
        return false;
    }

    //判断token是否快过期
    private boolean isTokenNearExpiration(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        long expirationTime = Long.parseLong(jwt.getPayload("exp").toString());
        long currentTime = System.currentTimeMillis();
        return (expirationTime - currentTime) < refreshThreshold;
    }

    private boolean isTokenExpired(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        long expirationTime = Long.parseLong(jwt.getPayload("exp").toString());
        return System.currentTimeMillis() > expirationTime;
    }


    //刷新token
    private String[] refreshTokens(String refreshToken) {
        JWT jwt = JWTUtil.parseToken(refreshToken);
        Map<String, Object> payload = new HashMap<>(jwt.getPayloads());
        
        long currentTimeMillis = System.currentTimeMillis();
        
        // 设置新的访问令牌过期时间
        payload.put("exp", currentTimeMillis + accessTokenExpiration);
        String newAccessToken = JWTUtil.createToken(payload, secretKey.getBytes());
        
        // 设置新的刷新令牌过期时间
        payload.put("exp", currentTimeMillis + refreshTokenExpiration);
        String newRefreshToken = JWTUtil.createToken(payload, secretKey.getBytes());
        
        return new String[]{newAccessToken, newRefreshToken};
    }

    private String getTokenFromRequest(org.springframework.http.server.reactive.ServerHttpRequest serverHttpRequest) {
        // 从请求头或其他地方获取访问令牌
        String token = serverHttpRequest.getHeaders().getFirst("access-token");
        return token;
    }

    private String getRefreshTokenFromRequest(org.springframework.http.server.reactive.ServerHttpRequest serverHttpRequest) {
        // 从请求头或其他地方获取刷新令牌
        String token = serverHttpRequest.getHeaders().getFirst("refresh-token");
        return token;
    }

    //处理未授权错误
    private Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        JSONConfig config = new JSONConfig().setIgnoreNullValue(false);
        BaseResponse<Object> response = BaseResponse.error(ErrorCode.UNAUTHORIZED);
        String responseBody = JSONUtil.toJsonStr(response, config);
        
        return exchange.getResponse().writeWith(
            Mono.just(exchange.getResponse().bufferFactory().wrap(responseBody.getBytes()))
        );
    }


}