package com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories;

import com.learning.myfitapp.modules.workout.application.repositories.WorkoutExerciseRepository;
import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutExerciseJpaEntity;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.mappers.WorkoutExerciseJpaMapper;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.repositories.jpa.WorkoutExerciseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        workoutExerciseJpaRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(UUID id) {
        return workoutExerciseJpaRepository.existsById(id);
    }

    @Override
    public void deleteAllByWorkoutId(UUID id) {
        workoutExerciseJpaRepository.deleteAllByWorkoutId(id);
    }

    @Override
    public List<WorkoutExercise> findAllByWorkoutId(UUID id) {
        return workoutExerciseJpaRepository.findAllByWorkoutId(id).stream().map(WORKOUT_EXERCISE_JPA_MAPPER::jpaToDomain).toList();
    }
}
