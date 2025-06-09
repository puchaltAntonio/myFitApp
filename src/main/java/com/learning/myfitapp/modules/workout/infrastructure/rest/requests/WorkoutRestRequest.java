package com.learning.myfitapp.modules.workout.infrastructure.rest.requests;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record WorkoutRestRequest(
        String name,
        List<UUID> exercisesIds,
        UUID profileId

) {
}
