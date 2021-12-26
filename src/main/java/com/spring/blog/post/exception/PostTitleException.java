package com.spring.blog.post.exception;

import org.springframework.http.HttpStatus;

public class PostTitleException extends PostException {

    private static final String ERROR_MESSAGE = "게시글은 공백이나 50자를 초과할 수 없습니다.";
    private static final String ERROR_CODE = "P0002";

    public PostTitleException() {
        this(ERROR_MESSAGE, ERROR_CODE, HttpStatus.BAD_REQUEST);
    }

    protected PostTitleException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
