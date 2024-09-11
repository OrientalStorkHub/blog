package com.orientalstorkhub.blog.userauthservice.controller;

import com.orientalstorkhub.blog.common.pojo.entity.auth.User;
import com.orientalstorkhub.blog.common.pojo.vo.user.LoginResponseVo;
import com.orientalstorkhub.blog.common.pojo.vo.user.UserLoginVo;
import com.orientalstorkhub.blog.common.pojo.vo.user.UserRegisterVO;
import com.orientalstorkhub.blog.common.responses.BaseResponse;
import com.orientalstorkhub.blog.userauthservice.service.UserService;

import cn.hutool.system.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "用户管理", description = "提供用户的增删改查接口")
@RestController
public class UserController {

  @Autowired
  UserService userService;

  /**
   * 用户注册接口
   * 
   * @param userRegisterVO 注册信息对象，包含用户名、邮箱、密码等信息
   * @return
   */
  @PostMapping(value = "/register", produces = "application/json")
  @Operation(summary = "注册")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "注册成功"),
      @ApiResponse(responseCode = "4008", description = "用户昵称已存在"),
      @ApiResponse(responseCode = "4009", description = "用户名已存在"),
      @ApiResponse(responseCode = "4010", description = "邮箱已存在"),
      @ApiResponse(responseCode = "4018", description = "用户注册失败"),
  })
  public BaseResponse<Object> register(@RequestBody UserRegisterVO userRegisterVO) {
    userService.register(userRegisterVO);
    return new BaseResponse<>(200, "注册成功", null);
  }

  /**
   * 用户登录接口
   * 
   * @param userVo 登录信息对象，包含用户名或邮箱和密码
   * @return 登录响应对象，包含用户信息和令牌信息
   */
  @PostMapping(value = "/login", produces = "application/json")
  @Operation(summary = "登录", description = "根据用户/邮箱、密码登录")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "登录成功"),
      @ApiResponse(responseCode = "400", description = "用户名或密码错误")
  })
  public BaseResponse<LoginResponseVo> login(@RequestBody UserLoginVo userVo) {
    Optional<LoginResponseVo> loginExistUser = userService.login(userVo);
    if (loginExistUser.isEmpty()) {
      return new BaseResponse<>(400, "用户名或密码错误", null);
    }
    return new BaseResponse<>(200, "登录成功", loginExistUser.get());
  }

  /**
   * 注销
   * 
   * @param accessToken
   * @return
   */
  @PostMapping(value = "/logout")
  @Operation(summary = "注销")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "注销成功")
  })
  public BaseResponse<Object> logout(@RequestHeader("access-token") String accessToken) {
    userService.logout(accessToken);
    return new BaseResponse<>(200, "登出成功", null);
  }

  @PostMapping(value = "/updateUserInfo")
  @Operation(summary = "修改用户信息")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "修改成功")
  })
  public BaseResponse<Object> updateUserInfo(@RequestBody User user) {
    userService.updateUserInfo(user);
    return new BaseResponse<>(200, "修改成功", null);
  }

}
