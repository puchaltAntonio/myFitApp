package com.learning.myfitapp.modules.workout.application.usecases.getworkout;

import com.learning.myfitapp.modules.workout.application.exceptions.WorkoutNotFoundException;
import com.learning.myfitapp.modules.workout.domain.models.Workout;

import java.util.UUID;

public interface GetWorkoutUseCase {
    Workout getWorkout(UUID id) throws WorkoutNotFoundException;
}
