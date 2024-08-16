package com.orientalstorkhub.blog.common.constants;

public enum LoginType {
    USERNAME(1,"用户名登录"),
    Email(2, "邮箱登录");

    // 字段定义
    private final String description;
    private final int code;

    LoginType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }


}
