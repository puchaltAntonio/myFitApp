package com.learning.myfitapp.common.domain.exception;

import java.util.List;

public abstract class DomainValidationException extends RuntimeException {
    private final List<? extends DomainErrorField> errors;

    protected DomainValidationException(List<? extends DomainErrorField> errors) {
        super("Domain validation error");
        this.errors = errors;
    }

    public List<? extends DomainErrorField> getErrors() {
        return errors;
    }
}
