package com.learning.myfitapp.modules.exercise.domain.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Exercise {

    private UUID id;

    private String name;

    private MuscleEnum primaryMuscle;

    private MuscleEnum secondaryMuscle;

    private EquipmentEnum equipment;

    private ExerciseAnimation animation;

    public Exercise(UUID id, String name, MuscleEnum primaryMuscle, MuscleEnum secondaryMuscle, EquipmentEnum equipment) {
        this.id = id;
        this.name = name;
        this.primaryMuscle = primaryMuscle;
        this.secondaryMuscle = secondaryMuscle;
        this.equipment = equipment;
    }
}
