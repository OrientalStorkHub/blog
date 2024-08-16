package com.orientalstorkhub.blog.userauthservice.service;

public interface TokenBlacklistService {
    void addToBlacklist(String token, long expiration);
    boolean isBlacklisted(String token);
}