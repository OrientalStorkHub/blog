package com.orientalstorkhub.blog.common.constants;

public enum RedisKey {
    //时间单位 - s
    JWT_TOKEN("jwt:token:", 900),
    REFRESH_TOKEN("jwt:refresh:", 2592000),
    BLACKLIST("jwt:blacklist:", 900);

    private final String prefix;
    private final long expirationTime;

    RedisKey(String prefix, long expirationTime) {
        this.expirationTime = expirationTime;
        this.prefix = prefix;
    }

    public long getExpirationTime(){
        return this.expirationTime;
    }

    public String getPrefix(){
        return this.prefix;
    }


}
