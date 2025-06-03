package com.learning.myfitapp.modules.profile.application.usecases.getprofile;

import com.learning.myfitapp.modules.profile.application.exceptions.ProfileNotFoundException;
import com.learning.myfitapp.modules.profile.domain.models.Profile;

import java.util.UUID;

public interface GetProfileUseCase {
    Profile getProfile(final UUID id) throws ProfileNotFoundException;
}
