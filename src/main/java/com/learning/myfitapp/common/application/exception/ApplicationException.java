package com.learning.myfitapp.common.application.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final Integer errorCode;

    public ApplicationException(String message, final Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
