package com.learning.myfitapp.modules.profile.application.usecases.deleteprofile;


import com.learning.myfitapp.modules.profile.application.exceptions.ProfileNotFoundException;
import com.learning.myfitapp.modules.profile.application.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteProfileUseCaseImpl implements DeleteProfileUseCase{

    private final ProfileRepository repository;

    @Override
    public void deleteProfile(UUID id) {
        if(!repository.existsById(id)){
            throw new ProfileNotFoundException(id);
        }
        repository.deleteById(id);

    }

}
