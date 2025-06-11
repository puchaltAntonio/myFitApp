package com.learning.myfitapp.modules.workout.application.usecases.updateworkout;

import com.learning.myfitapp.modules.workout.domain.models.Workout;

public interface UpdateWorkoutUseCase {
    Workout updateWorkout(UpdateWorkoutUseCaseRequest request);
}
