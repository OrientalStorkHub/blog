package com.orientalstorkhub.blog.userauthservice.service;

import com.orientalstorkhub.blog.common.model.vo.user.LoginResponseVo;
import com.orientalstorkhub.blog.common.model.vo.user.UserLoginVo;
import com.orientalstorkhub.blog.common.model.vo.user.UserRegisterVO;


import java.util.Optional;

public interface UserService {



    /**
     * 根据用户名或邮箱进行用户注册。
     * 
     * @param userRegisterVO 注册信息对象，包含用户名、邮箱、密码等信息
     */
    void register(UserRegisterVO userRegisterVO);

    
    /**
     * 根据用户名或邮箱进行用户登录。
     * 
     * @param userLoginVo 登录信息对象，包含用户名或邮箱和密码
     * @return Optional<LoginResponseVo> 登录响应对象，包含用户信息和令牌信息，可能为空
     */
    Optional<LoginResponseVo> login(UserLoginVo userLoginVo);

    /**
     * 根据访问令牌进行用户注销。
     * 
     * @param accessToken 访问令牌
     */
    void logout(String accessToken);
}
