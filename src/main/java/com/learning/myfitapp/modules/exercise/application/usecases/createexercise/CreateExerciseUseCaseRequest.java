package com.learning.myfitapp.modules.exercise.application.usecases.createexercise;

import com.learning.myfitapp.modules.exercise.domain.models.EquipmentEnum;
import com.learning.myfitapp.modules.exercise.domain.models.MuscleEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CreateExerciseUseCaseRequest {

    private String name;

    private MuscleEnum primaryMuscle;

    private MuscleEnum secondaryMuscle;

    private EquipmentEnum equipment;

    //TODO: Animation
}
