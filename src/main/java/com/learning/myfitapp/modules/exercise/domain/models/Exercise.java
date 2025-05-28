package com.learning.myfitapp.modules.exercise.domain.models;

import com.learning.myfitapp.modules.exercise.domain.exceptions.ExerciseErrorField;
import com.learning.myfitapp.modules.exercise.domain.exceptions.ExerciseValidationException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
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

        validateConstraints(
                name,
                primaryMuscle,
                secondaryMuscle,
                equipment
        );

        this.id = id;
        this.name = name;
        this.primaryMuscle = primaryMuscle;
        this.secondaryMuscle = secondaryMuscle;
        this.equipment = equipment;

    }


    public static void validateConstraints(
            String name,
            MuscleEnum primaryMuscle,
            MuscleEnum secondaryMuscle,
            EquipmentEnum equipment
    ) {
        final List<ExerciseErrorField> errors = new ArrayList<>();

        if (name == null || name.isBlank()) {
            errors.add(new ExerciseErrorField("name", "Name can not be blank."));
        }
        if (name != null && name.length() > 60) {
            errors.add(new ExerciseErrorField("name", "Name's max length is 60 characters."));
        }

        if (primaryMuscle == null || primaryMuscle.name().isBlank()) {
            errors.add(new ExerciseErrorField("primaryMuscle", "Primary muscle can not be blank."));
        }

        if (!errors.isEmpty()) {
            throw new ExerciseValidationException(errors);
        }
    }
}
