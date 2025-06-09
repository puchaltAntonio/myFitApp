package com.learning.myfitapp.modules.workout.application.repositories;

import com.learning.myfitapp.modules.exercise.domain.models.Exercise;
import com.learning.myfitapp.modules.workout.domain.models.Workout;

import java.util.Optional;
import java.util.UUID;

public interface WorkoutRepository {
    Workout save(final Workout workout);
    Optional<Workout> findById(final UUID id);
    void deleteById(final UUID id);
    Boolean existsById(final UUID id);
    Workout update(final Workout workout);
}
