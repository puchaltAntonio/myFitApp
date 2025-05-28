package com.learning.myfitapp.modules.exercise.domain.exceptions;

import com.learning.myfitapp.common.domain.exception.DomainValidationException;

import java.util.List;

public class ExerciseValidationException extends DomainValidationException {
    public ExerciseValidationException(List<ExerciseErrorField> errors) {
        super(errors);
    }
}
