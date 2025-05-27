package com.learning.myfitapp.modules.exercise.application.repositories;

import com.learning.myfitapp.modules.exercise.domain.models.Exercise;

public interface ExerciseRepository {
    Exercise save(final Exercise exercise);
}
