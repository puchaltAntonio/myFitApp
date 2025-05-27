package com.learning.myfitapp.modules.exercise.infrastructure.rest.responses;

import com.learning.myfitapp.modules.exercise.domain.models.EquipmentEnum;
import com.learning.myfitapp.modules.exercise.domain.models.MuscleEnum;

import java.util.UUID;

public record ExerciseRestResponse(
        UUID id,
        String name,
        MuscleEnum primaryMuscle,
        MuscleEnum secondaryMuscle,
        EquipmentEnum equipment
        //TODO: Animation
) {
}
