package com.learning.myfitapp.modules.profile.application.usecases.getprofile;

import com.learning.myfitapp.modules.profile.application.exceptions.ProfileNotFoundException;
import com.learning.myfitapp.modules.profile.application.repositories.ProfileRepository;
import com.learning.myfitapp.modules.profile.domain.models.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GetProfileUseCaseImpl implements GetProfileUseCase{

    private final ProfileRepository repository;

    @Override
    public Profile getProfile(UUID id) throws ProfileNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ProfileNotFoundException(id)
                );
    }
}
