package com.learning.myfitapp.common.domain.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class DomainValidationException extends RuntimeException {
    private final List<DomainErrorField> errors;

    public DomainValidationException(List<DomainErrorField> errors) {
        super("Domain validation error");
        this.errors = errors;
    }
}
