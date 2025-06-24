package com.learning.myfitapp.modules.workout.application.usecases.workout.updateworkout;

import com.learning.myfitapp.modules.workout.application.usecases.workout.createworkout.CreateWorkoutUseCaseRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UpdateWorkoutUseCaseRequest extends CreateWorkoutUseCaseRequest {
    private UUID id;

    public UpdateWorkoutUseCaseRequest(
            @NonNull JwtAuthenticationToken jwtToken,
            @NonNull String name,
            @NonNull List<UUID> workoutExercisesIds,
            @NonNull UUID id
            )
    {
        super(jwtToken, name, workoutExercisesIds);
        this.id = id;
    }
}
