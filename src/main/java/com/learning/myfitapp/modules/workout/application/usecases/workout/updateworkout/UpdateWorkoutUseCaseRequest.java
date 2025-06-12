package com.learning.myfitapp.modules.workout.application.usecases.workout.updateworkout;

import com.learning.myfitapp.modules.workout.application.usecases.workout.createworkout.CreateWorkoutUseCaseRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UpdateWorkoutUseCaseRequest extends CreateWorkoutUseCaseRequest {
    private UUID id;
}
