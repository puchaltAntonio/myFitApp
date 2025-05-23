package com.learning.myfitapp.modules.exercise.domain.models;

import java.util.UUID;

public class Exercise {

    private UUID id;

    private String name;

    private MuscleEnum primaryMuscle;

    private MuscleEnum secondaryMuscle;

    private Equipment equipment;

    private ExerciseAnimation animation;
}
