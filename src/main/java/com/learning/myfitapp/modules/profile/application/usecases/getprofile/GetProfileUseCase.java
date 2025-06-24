package com.learning.myfitapp.modules.profile.application.usecases.getprofile;

import com.learning.myfitapp.modules.profile.application.exceptions.ProfileNotFoundException;
import com.learning.myfitapp.modules.profile.domain.models.Profile;

public interface GetProfileUseCase {
    Profile getProfile(final GetProfileUseCaseRequest request) throws ProfileNotFoundException;
}
