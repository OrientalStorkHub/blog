package com.orientalstorkhub.blog.userauthservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orientalstorkhub.blog.common.model.vo.UserRegisterVO;
import com.orientalstorkhub.blog.common.model.vo.UserVo;
import com.orientalstorkhub.blog.common.responses.BaseResponse;
import com.orientalstorkhub.blog.userauthservice.service.UserService;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register", produces = "application/json")
    public BaseResponse<Object> register(@RequestBody UserRegisterVO userRegisterVO) {
        userService.register(userRegisterVO);
        return new BaseResponse<>(200, "注册成功", null);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public BaseResponse<Object> login(@RequestBody UserVo userVo) {
        Optional<UserVo> loginExistUser = userService.login(userVo);
        if (loginExistUser.isEmpty()) {
            if (userVo.getMode() == 1) {
                return new BaseResponse<>(400, "用户名或密码错误", null);
            }
            return new BaseResponse<>(400, "邮箱或密码错误", null);
        }
        return new BaseResponse<>(200, "登录成功", loginExistUser.get());
    }

}
