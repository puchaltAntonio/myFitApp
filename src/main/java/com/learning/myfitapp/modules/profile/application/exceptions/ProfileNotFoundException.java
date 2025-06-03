package com.learning.myfitapp.modules.profile.application.exceptions;

import com.learning.myfitapp.common.application.exception.ApplicationException;

import java.util.UUID;

public class ProfileNotFoundException extends ApplicationException {
    private static final int ERROR_CODE = 202;
    private static final String MESSAGE = "Profile with id %s not found";

    public ProfileNotFoundException(final UUID id) {
        super(String.format(MESSAGE, id), ERROR_CODE);
    }
}
