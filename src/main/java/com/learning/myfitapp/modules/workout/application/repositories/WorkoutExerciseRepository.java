package com.learning.myfitapp.modules.workout.application.repositories;

import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;

import java.util.Optional;
import java.util.UUID;

public interface WorkoutExerciseRepository {
    WorkoutExercise save(final WorkoutExercise workoutExercise);
    Optional<WorkoutExercise> findById(final UUID id);
    void deleteById(final UUID id);
    Boolean existsById(final UUID id);
    WorkoutExercise update(final WorkoutExercise workoutexercise);
}
