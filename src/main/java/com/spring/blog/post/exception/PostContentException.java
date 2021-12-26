package com.spring.blog.post.exception;

import org.springframework.http.HttpStatus;

public class PostContentException extends PostException{

    private static final String ERROR_MESSAGE = "게시글의 내용은 1 ~ 10000자까지만 가능합니다.";
    private static final String ERROR_CODE = "P0001";

    public PostContentException() {
        this(ERROR_MESSAGE, ERROR_CODE, HttpStatus.BAD_REQUEST);
    }

    protected PostContentException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
