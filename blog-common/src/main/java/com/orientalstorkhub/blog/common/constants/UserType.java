package com.orientalstorkhub.blog.common.constants;

/**
* Enum for defining user types within the system.
* 
* @author zhangj
* @since 2024-09-03 14:28:18
*/

public enum UserType {
    ADMIN("admin", 1), // 枚举常量定义
    NORMAL("normal user", 2); // 枚举常量定义

    // 字段定义
    private final String description;
    private final int code;

    // 构造函数
    UserType(String description, int code) {
        this.description = description;
        this.code = code;
    }

    // Getter 方法
    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}