package com.orientalstorkhub.blog.common.exception;

import com.orientalstorkhub.blog.common.constants.ErrorCode;

public class BlogBaseException extends RuntimeException {

    private final int errorCode;
    private final String errorMessage;

    public BlogBaseException(int errorCode, String errorMessage)
    {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BlogBaseException(ErrorCode errorCode)
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
