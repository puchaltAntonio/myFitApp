package com.learning.myfitapp.common.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() { super("Unauthorized action"); }

    public UnauthorizedException(String message) {
        super(message);
    }
}
