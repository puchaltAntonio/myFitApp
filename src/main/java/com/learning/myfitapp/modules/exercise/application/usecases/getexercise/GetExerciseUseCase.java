package com.learning.myfitapp.modules.exercise.application.usecases.getexercise;

import com.learning.myfitapp.modules.exercise.application.exceptions.ExerciseNotFoundException;
import com.learning.myfitapp.modules.exercise.domain.models.Exercise;

import java.util.UUID;

public interface GetExerciseUseCase {
    Exercise getExercise(final UUID id) throws ExerciseNotFoundException;
}
