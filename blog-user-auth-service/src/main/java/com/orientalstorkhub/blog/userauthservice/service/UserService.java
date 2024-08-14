package com.orientalstorkhub.blog.userauthservice.service;

import com.orientalstorkhub.blog.common.model.vo.UserRegisterVO;
import com.orientalstorkhub.blog.common.model.vo.UserVo;

import java.util.Optional;

public interface UserService {
    //用户注册
    void register(UserRegisterVO userRegisterVO);

    Optional<UserVo> login(UserVo userVo);
}
