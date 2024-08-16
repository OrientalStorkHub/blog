package com.orientalstorkhub.blog.common.constants;

public enum ErrorCode {

    UNAUTHORIZED(4001, "接口认证失败"),
    INVALID_TOKEN(4002, "无效的token"),
    INVALID_TOKEN_PARSING(4003, "无效的token解析"),
    LOGOUT_FAILED(4004, "用户登出失败"),
    USER_ALREADY_EXISTS(4009, "用户名已存在"),
    USER_EMAIL_ALREADY_EXISTS(4010, "邮箱已存在"),
    USERNAME_OR_PASSWORD_INCORRECT(4011, "用户名或密码不正确"),
    USER_REGISTER_FAILED(4018, "用户注册失败，但原因未知"),
    USER_LOGIN_FAILED(4019, "用户登录失败");
    
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
