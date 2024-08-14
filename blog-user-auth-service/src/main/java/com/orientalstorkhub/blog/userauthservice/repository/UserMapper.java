package com.orientalstorkhub.blog.userauthservice.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orientalstorkhub.blog.common.model.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM users WHERE username = #{username}")
    Optional<User> selectByUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE email = #{email}")
    Optional<User> selectByEmail(@Param("email") String email);

}
