package com.learning.myfitapp.modules.profile.application.usecases.createprofile;

import com.learning.myfitapp.modules.profile.domain.models.Profile;

public interface CreateProfileUseCase {
    Profile createProfile(final CreateProfileUseCaseRequest createProfileUseCaseRequest);
}
