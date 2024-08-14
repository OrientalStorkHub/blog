package com.orientalstorkhub.blog.common.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orientalstorkhub.blog.common.responses.BaseResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
@RestControllerAdvice
//@Component
public class GlobalExceptionHandler {
    // TODO: 返回值正确吗？
    @ExceptionHandler(BlogBaseException.class)
    public BaseResponse<Object> handleBlogBaseException(BlogBaseException e) {
        BaseResponse<Object> response = new BaseResponse<>(e.getErrorCode(), e.getErrorMessage(), null);
        System.out.print(e.getErrorMessage());
        return response;
    }


    public BaseResponse<Object> handleException(Exception e) {
        // 日志记录异常信息
        System.out.printf("服务器错误: ", e);
        return new BaseResponse<>(500, "服务器错误，请联系管理员", null);
    }
}
