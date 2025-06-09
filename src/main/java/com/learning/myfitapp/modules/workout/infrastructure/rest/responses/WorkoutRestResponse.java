package com.learning.myfitapp.modules.workout.infrastructure.rest.responses;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record WorkoutRestResponse(
        UUID id,
        String name,
        List<UUID> exercisesIds,
        UUID profileId
) {
}
