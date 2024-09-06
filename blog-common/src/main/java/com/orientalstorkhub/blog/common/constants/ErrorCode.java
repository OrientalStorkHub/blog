package com.orientalstorkhub.blog.common.constants;

public enum ErrorCode {

    


    UNAUTHORIZED(4001, "接口鉴权失败"),
    INVALID_TOKEN(4002, "无效token"),
    INVALID_TOKEN_PARSING(4003, "无效的token解析"),
    LOGOUT_FAILED(4004, "用户登出失败"),
    USER_NICKNAME_EXISTS(4008, "用户昵称已存在"),
    USER_ALREADY_EXISTS(4009, "用户名已存在"),
    USER_EMAIL_ALREADY_EXISTS(4010, "邮箱已存在"),
    USERNAME_OR_PASSWORD_INCORRECT(4011, "用户名或密码不正确"),
    USER_REGISTER_FAILED(4018, "用户注册失败"),
    USER_LOGIN_FAILED(4019, "用户登录失败"),
    USER_LOGIN_USERNAME_OR_EMAIL_FAILED(4020, "用户名或邮箱错误"),
    
   
    CONTENT_SERVICE_CATEGORY_EXISTS(5008, "分类名重复"),
    CONTENT_SERVICE_CATEGORY_ALREADY_EXISTS(5009, "内容服务类别已存在"),
    CONTENT_SERVICE_CATEGORY_EMAIL_ALREADY_EXISTS(5010, "内容服务类别邮箱已存在"),
    CONTENT_SERVICE_CATEGORY_UPDATE_FAILED(5018, "分类更新失败"),
    CONTENT_SERVICE_CATEGORY_INSERT_FAILED(5018, "分类插入失败"),
    CONTENT_SERVICE_CATEGORY_DELETE_FAILED(5019, "分类删除失败"),
    CONTENT_SERVICE_TAG_NAME_REPEAT(5021, "标签名存在重复项"),
    CONTENT_SERVICE_TAG_NAME_EXISTS(5022, "部分标签名已存在"),
    CONTENT_SERVICE_INSERT_TAG_FAILED(5023, "标签插入失败"),
    CONTENT_SERVICE_INSERT_TAG_NOT_EMPTY(5024, "标签名不能为空"),
    CONTENT_SERVICE_TAG_DELETE_FAILED(5019, "分类删除失败"),


    DATABASE_OPERATION_EXCEPTION(6001, "数据库操作异常，请联系管理员");



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
