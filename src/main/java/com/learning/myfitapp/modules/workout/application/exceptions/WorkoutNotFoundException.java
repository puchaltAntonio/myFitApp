package com.learning.myfitapp.modules.workout.application.exceptions;

import com.learning.myfitapp.common.application.exception.ApplicationException;

import java.util.UUID;

public class WorkoutNotFoundException extends ApplicationException {
    private static final int ERROR_CODE = 202;
    private static final String MESSAGE = "Workout with id %s not found";

    public WorkoutNotFoundException(final UUID id) {
        super(String.format(MESSAGE, id), ERROR_CODE);
    }
}
