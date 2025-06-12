package com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories.jpa;

import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface WorkoutJpaRepository extends JpaRepository<WorkoutJpaEntity, UUID>, JpaSpecificationExecutor<WorkoutJpaEntity> {
}
