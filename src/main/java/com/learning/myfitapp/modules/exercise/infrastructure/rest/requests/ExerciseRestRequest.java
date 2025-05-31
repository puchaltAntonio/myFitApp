package com.learning.myfitapp.modules.exercise.infrastructure.rest.requests;

import com.learning.myfitapp.modules.exercise.domain.models.EquipmentEnum;
import com.learning.myfitapp.modules.exercise.domain.models.MuscleEnum;
import lombok.Builder;

@Builder
public record ExerciseRestRequest(
        String name,
        MuscleEnum primaryMuscle,
        MuscleEnum secondaryMuscle,
        EquipmentEnum equipment
        //TODO: Animation
) {

}
