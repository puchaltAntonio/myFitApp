package com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories;

import com.learning.myfitapp.modules.exercise.infrastructure.persistance.mappers.ExerciseJpaMapper;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import com.learning.myfitapp.modules.workout.domain.models.Workout;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutJpaEntity;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.mappers.WorkoutJpaMapper;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories.jpa.WorkoutJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkoutRepositoryImpl implements WorkoutRepository {

    private final WorkoutJpaRepository repository;

    private static final WorkoutJpaMapper WORKOUT_JPA_MAPPER = WorkoutJpaMapper.INSTANCE;

    @Override
    public Workout save(Workout workout) {
        WorkoutJpaEntity workoutJpaEntity = WORKOUT_JPA_MAPPER.domainToJpa(workout);
        return WORKOUT_JPA_MAPPER.jpaToDomain(
                repository.save(
                        workoutJpaEntity
                )
        );
    }

    @Override
    public Optional<Workout> findById(UUID id) {
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
    public Workout update(Workout workout) {
        return null;
    }
}
