package com.learning.myfitapp.modules.profile.application.usecases.createprofile;

import com.learning.myfitapp.modules.exercise.application.repositories.ExerciseRepository;
import com.learning.myfitapp.modules.profile.application.repositories.ProfileRepository;
import com.learning.myfitapp.modules.profile.domain.models.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateProfileUseCaseImpl implements CreateProfileUseCase{

    private final ProfileRepository repository;

    @Override
    public Profile createProfile(CreateProfileUseCaseRequest createProfileUseCaseRequest) {

        Profile profile = new Profile(
                null,
                createProfileUseCaseRequest.getUsername()
        );

        return repository.save(profile);
    }
}
