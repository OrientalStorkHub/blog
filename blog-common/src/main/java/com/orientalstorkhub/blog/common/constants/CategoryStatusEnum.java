package com.orientalstorkhub.blog.common.constants;


/**
 * Enum for defining category status within the system.
 * 
 * @author zhangj
 * @since 2024-09-03 14:47:28
 */

public enum CategoryStatusEnum {

    ACTIVE(1, "正常"),
    INACTIVE(0, "删除");

    private final Integer status;
    private final String desc;

    CategoryStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
