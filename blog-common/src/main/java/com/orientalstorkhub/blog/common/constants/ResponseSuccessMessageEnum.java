package com.orientalstorkhub.blog.common.constants;

public enum ResponseSuccessMessageEnum {
    REGISTER_SUCCESS("注册成功"),
    LOGIN_SUCCESS("登录成功"),
    LOGOUT_SUCCESS("登出成功"),



    CATEGORY_INSERT_SUCCESS("分类插入成功"),
    CATEGORY_SELECT_SUCCESS("分类查询成功"),
    CATEGORY_DELETE_SUCCESS("分类删除成功"),
    CATEGORY_UPDATE_SUCCESS("分类更新成功"),
    TAG_INSERT_SUCCESS("标签插入成功"),
    TAG_SELECT_SUCCESS("标签查询成功"),
    TAG_DELETE_SUCCESS("标签删除成功"),
    TAG_UPDATE_SUCCESS("标签更新成功");


    private final String message;

    ResponseSuccessMessageEnum(String meaasge){
        this.message = meaasge;
    }

    public String getMessage(){
        return message;
    }
}
