package com.learning.myfitapp.modules.profile.application.repositories;


import com.learning.myfitapp.modules.profile.domain.models.Profile;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository {
    Profile save(final Profile profile);
    Optional<Profile> findById(final UUID id);
    void deleteById(final UUID id);
    Boolean existsById(final UUID id);
}
