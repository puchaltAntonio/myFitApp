package com.learning.myfitapp.modules.exercise.application.repositories;

import com.learning.myfitapp.modules.exercise.domain.models.Exercise;

import java.util.Optional;
import java.util.UUID;

public interface ExerciseRepository {
    Exercise save(final Exercise exercise);
    Optional<Exercise> findById(final UUID id);
}
