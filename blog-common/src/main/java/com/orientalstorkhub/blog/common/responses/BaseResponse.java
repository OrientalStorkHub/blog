package com.orientalstorkhub.blog.common.responses;

import java.io.Serializable;

import lombok.Data;


@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
