package com.orientalstorkhub.blog.common.utils;

import com.orientalstorkhub.blog.common.constants.ResponseSuccessMessageEnum;
import com.orientalstorkhub.blog.common.responses.BaseResponse;

/**
 * @description Response utility class for generating standardized responses
 * @author zhangj
 * @since 2024-09-03 15:09:12
 */

public class ResponseUtil {
    public static <T> BaseResponse<T> success(ResponseSuccessMessageEnum responseMessage) {
        return new BaseResponse<>(200, responseMessage.getMessage(), null);
    }

    public static <T> BaseResponse<T> success(ResponseSuccessMessageEnum responseMessage, T data) {
        return new BaseResponse<>(200, responseMessage.getMessage(), data);
    }
}
