package com.learning.myfitapp.modules.profile.infrastructure.persistance.repositories;

import com.learning.myfitapp.modules.profile.application.repositories.ProfileRepository;
import com.learning.myfitapp.modules.profile.domain.models.Profile;
import com.learning.myfitapp.modules.profile.infrastructure.persistance.entities.ProfileJpaEntity;
import com.learning.myfitapp.modules.profile.infrastructure.persistance.mappers.ProfileJpaMapper;
import com.learning.myfitapp.modules.profile.infrastructure.persistance.repositories.jpa.ProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository repository;

    private static final ProfileJpaMapper PROFILE_JPA_MAPPER = ProfileJpaMapper.INSTANCE;

    @Override
    public Profile save(Profile profile) {

        final ProfileJpaEntity profileJpaEntity = PROFILE_JPA_MAPPER.profileToJpa(profile);

        return PROFILE_JPA_MAPPER.jpaToProfile(
                repository.save(profileJpaEntity)
        );
    }

    @Override
    public Optional<Profile> findById(UUID id) {
        return repository.findById(id)
                .map(PROFILE_JPA_MAPPER::jpaToProfile);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
