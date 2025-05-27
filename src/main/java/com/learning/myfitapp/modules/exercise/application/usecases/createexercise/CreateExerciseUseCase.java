package com.learning.myfitapp.modules.exercise.application.usecases.createexercise;

import com.learning.myfitapp.modules.exercise.domain.models.Exercise;

public interface CreateExerciseUseCase {
    Exercise createExercise(final CreateExerciseUseCaseRequest createExerciseUseCaseRequest);
}
