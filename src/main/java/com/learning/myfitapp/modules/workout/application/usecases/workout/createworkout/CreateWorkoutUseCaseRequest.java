package com.learning.myfitapp.modules.workout.application.usecases.workout.createworkout;

import com.learning.myfitapp.common.application.usecase.AuthenticatedUseCaseRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateWorkoutUseCaseRequest extends AuthenticatedUseCaseRequest {
    private String name;

    private List<UUID> workoutExercisesIds;


    public CreateWorkoutUseCaseRequest(
            @NonNull final JwtAuthenticationToken jwtToken,
            @NonNull final String name,
            @NonNull final List<UUID> workoutExercisesIds
    ){
        super(jwtToken);
        this.name = name;
        this.workoutExercisesIds = workoutExercisesIds;
    }
}
