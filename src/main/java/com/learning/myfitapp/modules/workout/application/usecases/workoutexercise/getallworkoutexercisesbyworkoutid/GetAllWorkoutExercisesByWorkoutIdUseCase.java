package com.learning.myfitapp.modules.workout.application.usecases.workoutexercise.getallworkoutexercisesbyworkoutid;

import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;

import java.util.List;
import java.util.UUID;

public interface GetAllWorkoutExercisesByWorkoutIdUseCase {
    List<WorkoutExercise> getAllWorkoutExerciseByWorkoutId(UUID id);
}
