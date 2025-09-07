package com.learning.myfitapp.modules.workout.application.usecases.workout.deleteworkout;

import com.learning.myfitapp.common.application.usecase.AuthenticatedUseCaseRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DeleteWorkoutUseCaseRequest extends AuthenticatedUseCaseRequest {
    private UUID workoutId;


    public DeleteWorkoutUseCaseRequest(
            @NonNull final JwtAuthenticationToken jwtToken,
            @NonNull final UUID workoutId)
    {
        super(jwtToken);
        this.workoutId = workoutId;
    }

}
