package com.learning.myfitapp.modules.profile.application.usecases.deleteprofile;

import java.util.UUID;

public interface DeleteProfileUseCase {
    void deleteProfile(final UUID id);
}
