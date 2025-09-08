package com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories.jpa;

import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutExerciseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface WorkoutExerciseJpaRepository extends JpaRepository<WorkoutExerciseJpaEntity, UUID>, JpaSpecificationExecutor<WorkoutExerciseJpaRepository> {

    @Modifying
    @Transactional
    void deleteAllByWorkoutId(final UUID id);

    @Modifying
    @Transactional
    List<WorkoutExerciseJpaEntity> findAllByWorkoutId(final UUID id);
}
