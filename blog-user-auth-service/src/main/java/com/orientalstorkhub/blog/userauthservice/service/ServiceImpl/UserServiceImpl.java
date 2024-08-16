package com.orientalstorkhub.blog.userauthservice.service.ServiceImpl;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import cn.hutool.jwt.JWTUtil;
import com.orientalstorkhub.blog.common.config.JwtConfig;
import com.orientalstorkhub.blog.common.constants.LoginType;

import com.orientalstorkhub.blog.common.model.vo.user.LoginResponseVo;
import com.orientalstorkhub.blog.common.model.vo.user.UserLoginVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orientalstorkhub.blog.common.constants.ErrorCode;
import com.orientalstorkhub.blog.common.constants.UserType;
import com.orientalstorkhub.blog.common.exception.BlogBaseException;
import com.orientalstorkhub.blog.common.model.entity.User;
import com.orientalstorkhub.blog.common.model.vo.user.UserRegisterVO;
import com.orientalstorkhub.blog.common.utils.PWDUtil;
import com.orientalstorkhub.blog.userauthservice.repository.UserMapper;
import com.orientalstorkhub.blog.userauthservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtConfig jwtConfig;



    @Override
    public void register(UserRegisterVO userRegisterVO) {
        // 验证用户名是否已存在
        if (userMapper.selectByUsername(userRegisterVO.getUsername()).isPresent()) {
            throw new BlogBaseException(ErrorCode.USER_ALREADY_EXISTS);
        }
        //验证邮箱是否已存在
        if (userMapper.selectByEmail(userRegisterVO.getEmail()).isPresent()) {
            throw new BlogBaseException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }

        String salt = PWDUtil.generateSalt();
        User user = User.builder().username(userRegisterVO.getUsername())
                .email(userRegisterVO.getEmail())
                .password(PWDUtil.hashPassword(userRegisterVO.getPassword(), salt))
                .role(UserType.NORMAL.getCode())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .salt(salt)
                .build();
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            throw new BlogBaseException(ErrorCode.USER_REGISTER_FAILED);
        }
    }

    @Override
    public Optional<LoginResponseVo> login(UserLoginVo userVo) {
        User user = null;
        if (userVo != null && userVo.getMode() != null) {
            Optional<User> existingUser = Optional.empty();
            if (userVo.getMode() == LoginType.USERNAME.getCode()) {
                //用户名登录
                existingUser = userMapper.selectByUsername(userVo.getUsername());
            } else if (userVo.getMode() == LoginType.Email.getCode()) {
                //邮箱登录
                existingUser = userMapper.selectByEmail(userVo.getEmail());
            }
            if (existingUser.isPresent()) {
                user = existingUser.get();
            }
        }
        try {
            if (user != null && PWDUtil.verifyPassword(userVo.getPassword(), user.getPassword(), user.getSalt())) {
                // 生成accessToken和refreshToken
                String accessToken = generateJwtToken(user.getId(), jwtConfig.getRefreshTokenExpiration());
                String refreshToken = generateJwtToken(user.getId(), jwtConfig.getAccessTokenExpiration());
                
                if (!isValidJwtFormat(accessToken) || !isValidJwtFormat(refreshToken)) {
                    throw new IllegalStateException("Generated JWT token is not in valid format");
                }
                
                long accessTokenExpireTime = System.currentTimeMillis() + jwtConfig.getAccessTokenExpiration();
                long refreshTokenExpireTime = System.currentTimeMillis() + jwtConfig.getAccessTokenExpiration();
                
                return Optional.of(LoginResponseVo.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .expiresAt(accessTokenExpireTime)
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .refreshExpiresAt(refreshTokenExpireTime)
                        .email(user.getEmail())
                        .build());
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new BlogBaseException(ErrorCode.USER_LOGIN_FAILED);
        }
        return Optional.empty();
    }

    private boolean isValidJwtFormat(String token) {
        return token != null && token.split("\\.").length == 3;
    }

    private String generateJwtToken(Integer userId, long expiration) {
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + expiration;
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", userId);
        claims.put("iat", currentTime);
        claims.put("exp", expireTime);
        
        String token = JWTUtil.createToken(claims, jwtConfig.getSecretKey().getBytes());
        System.out.println("Generated JWT token: " + token);
        return token;
    }
}