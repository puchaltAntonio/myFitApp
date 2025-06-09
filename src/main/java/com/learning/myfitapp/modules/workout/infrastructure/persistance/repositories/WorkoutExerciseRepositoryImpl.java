package com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories;

import com.learning.myfitapp.modules.workout.application.repositories.WorkoutExerciseRepository;
import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutExerciseJpaEntity;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.mappers.WorkoutExerciseJpaMapper;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories.jpa.WorkoutExerciseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkoutExerciseRepositoryImpl implements WorkoutExerciseRepository {

    private final WorkoutExerciseJpaRepository workoutExerciseJpaRepository;
    private static final WorkoutExerciseJpaMapper WORKOUT_EXERCISE_JPA_MAPPER = WorkoutExerciseJpaMapper.INSTANCE;

    @Override
    public WorkoutExercise save(WorkoutExercise workoutExercise) {

        final WorkoutExerciseJpaEntity workoutExerciseJpaEntity = WORKOUT_EXERCISE_JPA_MAPPER.domainToJpa(workoutExercise);

        return WORKOUT_EXERCISE_JPA_MAPPER.jpaToDomain(workoutExerciseJpaRepository.save(workoutExerciseJpaEntity));

    }

    @Override
    public Optional<WorkoutExercise> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public Boolean existsById(UUID id) {
        return null;
    }

    @Override
    public WorkoutExercise update(WorkoutExercise workoutexercise) {
        return null;
    }
}
