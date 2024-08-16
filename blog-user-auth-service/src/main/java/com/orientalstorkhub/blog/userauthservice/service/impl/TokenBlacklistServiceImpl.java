package com.orientalstorkhub.blog.userauthservice.service.impl;

import com.orientalstorkhub.blog.common.constants.RedisKeyPrefix;
import com.orientalstorkhub.blog.userauthservice.service.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void addToBlacklist(String token, long expiration) {
        long ttl = expiration - System.currentTimeMillis() / 1000;
        if (ttl > 0) {
            redisTemplate.opsForValue().set(RedisKeyPrefix.BLACKLIST.getPrefix() + token, "1", ttl, TimeUnit.SECONDS);
        }
    }

    @Override
    public boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(RedisKeyPrefix.BLACKLIST.getPrefix() + token));
    }


}