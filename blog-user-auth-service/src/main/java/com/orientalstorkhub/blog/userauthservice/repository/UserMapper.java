package com.orientalstorkhub.blog.userauthservice.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orientalstorkhub.blog.common.pojo.entity.auth.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM users WHERE username = #{username}")
    Optional<User> selectByUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE email = #{email}")
    Optional<User> selectByEmail(@Param("email") String email);

    /**
     * 根据用户名或邮箱查询用户信息。
     * 
     * @param nameOrEmail 用户名或邮箱
     * @return 用户信息对象，可能为空
     */
    @Select("SELECT * FROM users WHERE  username = #{nameOrEmail} or email = #{nameOrEmail} limit 1")
    Optional<User> selectByUsernameOrEmail(@Param("nameOrEmail") String nameOrEmail);

    /**
     * 根据用户名、邮箱、或昵称查询用户信息。
     * 
     * @param username 用户名
     * @param email 邮箱
     * @param nickname 昵称
     * @return User 用户信息对象，可能为空
     * @see com.orientalstorkhub.blog.common.pojo.entity.auth.User
     */
    @Select("SELECT * FROM user WHERE username = #{username} or email = #{email} or nickname = #{nickname} limit 1")
    Optional<User> selectByUsernameOrEmailOrNickname(@Param("username") String username,
        @Param("email") String email, @Param("nickname") String nickname);

}
