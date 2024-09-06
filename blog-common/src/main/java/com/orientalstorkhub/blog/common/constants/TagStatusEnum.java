package com.orientalstorkhub.blog.common.constants;

public enum TagStatusEnum {

    ACTIVE(Short.valueOf((short) 1), "激活"),
    INACTIVE(Short.valueOf((short) 0), "删除");

    private Short status;
    private String desc;

    TagStatusEnum(Short status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Short getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
