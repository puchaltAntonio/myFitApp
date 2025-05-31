package com.learning.myfitapp.modules.exercise.application.exceptions;

import com.learning.myfitapp.common.application.exception.ApplicationException;

import java.util.UUID;

public class ExerciseNotFoundException extends ApplicationException {
    private static final int ERROR_CODE = 202;
    private static final String MESSAGE = "Exercise with id %s not found";

    public ExerciseNotFoundException(final UUID id) {
        super(String.format(MESSAGE, id), ERROR_CODE);
    }
}
