package com.learning.myfitapp.modules.workout.application.usecases.deleteworkout;

import com.learning.myfitapp.modules.workout.application.exceptions.WorkoutNotFoundException;

import java.util.UUID;

public interface DeleteWorkoutUseCase {
    void deleteWorkout(UUID id) throws WorkoutNotFoundException;
}
