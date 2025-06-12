package com.learning.myfitapp.modules.workout.application.usecases.workoutexercise.createworkoutexerciseusecase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class CreateWorkoutExerciseUseCaseRequest {
    private UUID workoutId;

    private UUID exerciseId;

    private Integer order;
}
