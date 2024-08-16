package com.orientalstorkhub.blog.userauthservice.service;

import com.orientalstorkhub.blog.common.model.vo.user.LoginResponseVo;
import com.orientalstorkhub.blog.common.model.vo.user.UserLoginVo;
import com.orientalstorkhub.blog.common.model.vo.user.UserRegisterVO;


import java.util.Optional;

public interface UserService {
    //用户注册
    void register(UserRegisterVO userRegisterVO);

    Optional<LoginResponseVo> login(UserLoginVo userLoginVo);

    //登出
    void logout(String accessToken);
}
