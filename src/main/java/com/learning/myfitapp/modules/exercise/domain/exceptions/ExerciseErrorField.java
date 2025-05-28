package com.learning.myfitapp.modules.exercise.domain.exceptions;

import com.learning.myfitapp.common.domain.exception.DomainErrorField;

public class ExerciseErrorField extends DomainErrorField {
    public ExerciseErrorField(String field, String message) {
        super(field, message);
    }
}