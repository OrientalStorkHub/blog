package com.orientalstorkhub.blog.userauthservice.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orientalstorkhub.blog.common.pojo.entity.auth.UserSession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSessionMapper extends BaseMapper<UserSession> {
}
