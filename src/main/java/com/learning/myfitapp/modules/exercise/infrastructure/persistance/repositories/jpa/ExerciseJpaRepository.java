package com.learning.myfitapp.modules.exercise.infrastructure.persistance.repositories.jpa;

import com.learning.myfitapp.modules.exercise.infrastructure.persistance.entities.ExerciseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExerciseJpaRepository extends JpaRepository<ExerciseJpaEntity, UUID>, JpaSpecificationExecutor<ExerciseJpaEntity> {

}
