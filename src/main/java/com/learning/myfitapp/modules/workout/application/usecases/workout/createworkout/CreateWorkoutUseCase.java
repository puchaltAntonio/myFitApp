package com.learning.myfitapp.modules.workout.application.usecases.workout.createworkout;

import com.learning.myfitapp.modules.workout.domain.models.Workout;

public interface CreateWorkoutUseCase {
    Workout createWorkout(CreateWorkoutUseCaseRequest request);
}
