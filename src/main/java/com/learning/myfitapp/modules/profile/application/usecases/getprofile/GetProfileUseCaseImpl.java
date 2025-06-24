package com.learning.myfitapp.modules.profile.application.usecases.getprofile;

import com.learning.myfitapp.modules.profile.application.exceptions.ProfileNotFoundException;
import com.learning.myfitapp.modules.profile.application.repositories.ProfileRepository;
import com.learning.myfitapp.modules.profile.domain.models.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetProfileUseCaseImpl implements GetProfileUseCase{

    private final ProfileRepository repository;

    @Override
    public Profile getProfile(final GetProfileUseCaseRequest request) throws ProfileNotFoundException {

        return getOrCreateProfileIfNotExists(request);
    }

    private Profile getOrCreateProfileIfNotExists(final GetProfileUseCaseRequest request) {

        final Optional<Profile> optionalProfile = repository.findById(request.getId());

        return Boolean.TRUE.equals(request.getCreateIfNoExists())
                ? optionalProfile.map(
                        storedProfile -> {
                            if(
                                    !storedProfile.getName().equals(request.getName()) ||
                                    !storedProfile.getSurname().equals(request.getSurname()) ||
                                    !storedProfile.getEmail().equals(request.getEmail())
                            ) {
                                storedProfile.setEmail(request.getEmail());
                                storedProfile.setName(request.getName());
                                storedProfile.setSurname(request.getSurname());
                            }
                            return storedProfile;
                        }
                ).orElseGet(
                () -> repository.save(
                                new Profile(
                                        request.getId(),
                                        request.getName(),
                                        request.getSurname(),
                                        request.getEmail()
                                )
                        )
        )

                : optionalProfile.orElseThrow(
                        () -> new ProfileNotFoundException(request.getId())
                );
    }

}
