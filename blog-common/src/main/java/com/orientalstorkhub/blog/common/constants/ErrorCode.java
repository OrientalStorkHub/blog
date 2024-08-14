package com.orientalstorkhub.blog.common.constants;

public enum ErrorCode {


    USER_ALREADY_EXISTS(409, "用户名已存在"),
    USER_EMAIL_ALREADY_EXISTS(410, "邮箱已存在"),
    USERNAME_OR_PASSWORD_INCORRECT(401, "用户名或密码不正确"),
    USER_REGISTER_FAILED(408, "用户注册失败，但原因未知");
    // 更多错误类型...

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
