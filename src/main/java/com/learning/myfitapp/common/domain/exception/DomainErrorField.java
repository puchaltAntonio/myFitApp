package com.learning.myfitapp.common.domain.exception;

import lombok.Getter;

@Getter
public class DomainErrorField {
    private final String field;
    private final String message;

    public DomainErrorField(String field, String message) {
        this.field = field;
        this.message = message;
    }
}