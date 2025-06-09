package com.learning.myfitapp.modules.workout.application.usecases.createworkoutexerciseusecase;

import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;

public interface CreateWorkoutExerciseUseCase {
    WorkoutExercise createWorkoutExercise(final CreateWorkoutExerciseUseCaseRequest request);
}
