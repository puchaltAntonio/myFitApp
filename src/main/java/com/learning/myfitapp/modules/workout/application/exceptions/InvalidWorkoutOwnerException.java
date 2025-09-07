package com.learning.myfitapp.modules.workout.application.exceptions;

import com.learning.myfitapp.common.application.exception.ApplicationException;

import java.util.UUID;

public class InvalidWorkoutOwnerException extends ApplicationException {
    private static final int ERROR_CODE = 202;
    private static final String MESSAGE = "User requesting the operation is not the owner of workout with id: %s";

    public InvalidWorkoutOwnerException(final UUID id) {
        super(String.format(MESSAGE, id), ERROR_CODE);
    }
}
