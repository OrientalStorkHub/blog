package com.orientalstorkhub.blog.userauthservice.service.ServiceImpl;

import java.nio.file.OpenOption;
import java.sql.Timestamp;
import java.util.Optional;

import com.orientalstorkhub.blog.common.model.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.orientalstorkhub.blog.common.constants.ErrorCode;
import com.orientalstorkhub.blog.common.constants.UserType;
import com.orientalstorkhub.blog.common.exception.BlogBaseException;
import com.orientalstorkhub.blog.common.model.entity.User;
import com.orientalstorkhub.blog.common.model.vo.UserRegisterVO;
import com.orientalstorkhub.blog.common.utils.PWDUtil;
import com.orientalstorkhub.blog.userauthservice.repository.UserMapper;
import com.orientalstorkhub.blog.userauthservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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

        String salt =  PWDUtil.generateSalt();
        User user = User.builder().username(userRegisterVO.getUsername())
        .email(userRegisterVO.getEmail())
        .password(PWDUtil.hashPassword(userRegisterVO.getPassword(), salt))
        .role(UserType.NORMAL.getCode())
        .createdAt(new Timestamp(System.currentTimeMillis()))
        .updatedAt(new Timestamp(System.currentTimeMillis()))
        .salt(salt)
        .build();
         try{
             userMapper.insert(user);
         }catch (Exception e){
             throw new BlogBaseException(ErrorCode.USER_REGISTER_FAILED);
         }
    }

    @Override
    public Optional<UserVo> login(UserVo userVo) {
        //用户名登录
        if(userVo.getMode() == 1) {
            Optional<User> existingUser =  userMapper.selectByUsername(userVo.getUsername());
            if(existingUser.isPresent()) {
                User user = existingUser.get();
                if(PWDUtil.verifyPassword(userVo.getPassword(), user.getPassword(), user.getSalt())) {
                    return Optional.ofNullable(UserVo.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).role(user.getRole()).build());
                }
            }
        }else if(userVo.getMode() == 2) {
            Optional<User> existingUser =  userMapper.selectByEmail(userVo.getEmail());
            if(existingUser.isPresent()) {
                User user = existingUser.get();
                if(PWDUtil.verifyPassword(userVo.getPassword(), user.getPassword(), user.getSalt())) {
                    return Optional.ofNullable(UserVo.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).role(user.getRole()).build());
                }
            }
        }
        return Optional.empty();
    }
}
