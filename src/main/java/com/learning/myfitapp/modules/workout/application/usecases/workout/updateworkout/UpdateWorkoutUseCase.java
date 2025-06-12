package com.learning.myfitapp.modules.workout.application.usecases.workout.updateworkout;

import com.learning.myfitapp.modules.workout.domain.models.Workout;

public interface UpdateWorkoutUseCase {
    Workout updateWorkout(UpdateWorkoutUseCaseRequest request);
}
