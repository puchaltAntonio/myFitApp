package com.learning.myfitapp.modules.profile.infrastructure.persistance.repositories.jpa;

import com.learning.myfitapp.modules.profile.infrastructure.persistance.entities.ProfileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProfileJpaRepository extends JpaRepository<ProfileJpaEntity, UUID>, JpaSpecificationExecutor<ProfileJpaEntity> {
}
