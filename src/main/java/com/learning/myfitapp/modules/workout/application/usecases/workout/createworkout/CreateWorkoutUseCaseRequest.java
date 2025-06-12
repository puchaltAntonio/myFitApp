package com.learning.myfitapp.modules.workout.application.usecases.workout.createworkout;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class CreateWorkoutUseCaseRequest {
    private String name;

    private UUID profileId;

    private List<UUID> workoutExercisesIds;
}
