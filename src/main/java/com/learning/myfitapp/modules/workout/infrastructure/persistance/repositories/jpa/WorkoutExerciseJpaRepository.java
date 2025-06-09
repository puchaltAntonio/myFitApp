package com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories.jpa;

import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutExerciseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface WorkoutExerciseJpaRepository extends JpaRepository<WorkoutExerciseJpaEntity, UUID>, JpaSpecificationExecutor<WorkoutExerciseJpaRepository> {
}
