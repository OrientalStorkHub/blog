package com.orientalstorkhub.blog.common.constants;

public enum RedisKeyPrefix {
    //时间单位 - s
    JWT_TOKEN("jwt:token:"),
    REFRESH_TOKEN("jwt:refresh:"),
    BLACKLIST("jwt:blacklist:"),
    REVOKE_LIST("jwt:revoke:");

    private final String prefix;

    RedisKeyPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix(){
        return this.prefix;
    }


}
