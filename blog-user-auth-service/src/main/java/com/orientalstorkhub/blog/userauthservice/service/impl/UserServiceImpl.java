package com.orientalstorkhub.blog.userauthservice.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import cn.hutool.jwt.JWTUtil;
import com.orientalstorkhub.blog.common.config.JwtConfig;
import com.orientalstorkhub.blog.common.pojo.entity.auth.User;
import com.orientalstorkhub.blog.common.pojo.vo.user.LoginResponseVo;
import com.orientalstorkhub.blog.common.pojo.vo.user.UserLoginVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orientalstorkhub.blog.common.constants.ErrorCode;
import com.orientalstorkhub.blog.common.constants.UserType;
import com.orientalstorkhub.blog.common.exception.BlogBaseException;
import com.orientalstorkhub.blog.common.pojo.vo.user.UserRegisterVO;
import com.orientalstorkhub.blog.common.utils.PWDUtil;
import com.orientalstorkhub.blog.userauthservice.repository.UserMapper;
import com.orientalstorkhub.blog.userauthservice.service.UserService;
import com.orientalstorkhub.blog.userauthservice.service.TokenBlacklistService;

/**
 * UserServiceImpl is a service implementation class for User.
 * 
 * @author zhangj
 */

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private JwtConfig jwtConfig;
  @Autowired
  private TokenBlacklistService tokenBlacklistService;

  @Override
  public void register(UserRegisterVO userRegisterVO) {
    Optional<User> existUser = userMapper.selectByUsernameOrEmailOrNickname(userRegisterVO.getUsername(),
        userRegisterVO.getEmail(), userRegisterVO.getNickname());
    if (existUser.isPresent()) {
      User user = existUser.get();
      if (user.getUsername().equals(userRegisterVO.getUsername())) {
        throw new BlogBaseException(ErrorCode.USER_ALREADY_EXISTS);
      }
      if (user.getEmail().equals(userRegisterVO.getEmail())) {
        throw new BlogBaseException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
      }
      if (user.getNickname().equals(userRegisterVO.getNickname())) {
        throw new BlogBaseException(ErrorCode.USER_NICKNAME_EXISTS);
      }
    }
    String salt = PWDUtil.generateSalt();
    User user = User.builder().username(userRegisterVO.getUsername())
        .nickname(userRegisterVO.getNickname())
        .email(userRegisterVO.getEmail())
        .password(PWDUtil.hashPassword(userRegisterVO.getPassword(), salt))
        .role(UserType.NORMAL.getCode())
        .createdAt(Timestamp.from(Instant.now()))
        .updatedAt(Timestamp.from(Instant.now()))
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
    Optional<User> existingUser = userMapper.selectByUsernameOrEmail(userVo.getUsernameOrEmail());
    if (existingUser.isEmpty()) {
      throw new BlogBaseException(ErrorCode.USER_LOGIN_USERNAME_OR_EMAIL_FAILED);
    }
    User user = existingUser.get();
    try {
      // 验证密码
      if (!PWDUtil.verifyPassword(userVo.getPassword(), user.getPassword(), user.getSalt())) {
        return Optional.empty(); // 直接返回空
      }
      // 生成accessToken和refreshToken
      String accessToken = generateJwtToken(user.getId(), jwtConfig.getAccessTokenExpiration());
      String refreshToken = generateJwtToken(user.getId(), jwtConfig.getRefreshTokenExpiration());

      if (!isValidJwtFormat(accessToken) || !isValidJwtFormat(refreshToken)) {
        throw new IllegalStateException("Generated JWT token is not in valid format");
      }

      // 处理user_session

      return Optional.of(LoginResponseVo.builder()
          .userId(user.getId())
          .username(user.getUsername())
          .nickname(user.getNickname())
          .expiresAt(Timestamp.from(Instant.now().plusMillis(jwtConfig.getAccessTokenExpiration())))
          .accessToken(accessToken)
          .refreshToken(refreshToken)
          .refreshExpiresAt(Timestamp.from(Instant.now().plusMillis(jwtConfig.getRefreshTokenExpiration())))
          .email(user.getEmail())
          .build());
    } catch (Exception e) {
      System.out.println(e);
      throw new BlogBaseException(ErrorCode.USER_LOGIN_FAILED);
    }
  }

  // 验证JWT token格式
  private boolean isValidJwtFormat(String token) {
    return token != null && token.split("\\.").length == 3;
  }

  @Override
  public void logout(String accessToken) {
    // 验证令牌
    if (!isValidJwtFormat(accessToken)) {
      throw new BlogBaseException(ErrorCode.INVALID_TOKEN);
    }

    try {
      // 解析令牌以获取过期时间
      long expTime;
      try {
        expTime = JWTUtil.parseToken(accessToken).getPayload().getClaimsJson().getLong("exp");
      } catch (Exception e) {
        System.out.println(e);
        throw new BlogBaseException(ErrorCode.INVALID_TOKEN_PARSING);
      }

      // 将令牌添加到黑名单
      tokenBlacklistService.addToBlacklist(accessToken, expTime);
    } catch (Exception e) {
      System.out.println(e);
      throw new BlogBaseException(ErrorCode.LOGOUT_FAILED);
    }
  }

  /**
   * 生成jwt token
   * 
   * @param userId     用户id
   * @param expiration JWT有效时长
   * @return
   */
  private String generateJwtToken(Integer userId, long expiration) {
    long currentTime = System.currentTimeMillis();
    long expireTime = currentTime + expiration;

    Map<String, Object> claims = new HashMap<>();
    claims.put("uid", userId);
    claims.put("iat", currentTime);
    claims.put("exp", expireTime);

    String token = JWTUtil.createToken(claims, jwtConfig.getSecretKey().getBytes());
    return token;
  }

  /**
   * 修改用户信息
   */
  @Override
  public void updateUserInfo(User user) {
    Integer id = user.getId();
    User userEntity = userMapper.selectById(id);
    userEntity.setNickname(user.getNickname());
    userMapper.updateById(userEntity);
  }

}