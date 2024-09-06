package com.orientalstorkhub.blog.common.exception;

import com.orientalstorkhub.blog.common.constants.ErrorCode;

public class DAEOException extends RuntimeException {
    private final String errorMessage;
    private final int errorCode;
    
    public DAEOException(int errorCode, String errorMessage)
    {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DAEOException(ErrorCode errorCode)
    {
        super(errorCode.getDescription());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getDescription();
    }

    public int getErrorCode()
    {
        return errorCode;
    }   

    public String getErrorMessage()
    {
        return errorMessage;
    }
}