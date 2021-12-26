package com.spring.blog.post.exception;

import com.spring.blog.global.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public abstract class PostException extends BusinessException {

    protected PostException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
