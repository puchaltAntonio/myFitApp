package com.learning.myfitapp.modules.workout.domain.models;

import com.learning.myfitapp.common.domain.exception.DomainErrorField;
import com.learning.myfitapp.common.domain.exception.DomainValidationException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class WorkoutExercise {

    private UUID id;

    private UUID workoutId;

    private UUID exerciseId;

    private Integer order;

    public WorkoutExercise(UUID id, UUID workoutId, UUID exerciseId, Integer order) {

        validateConstraints(order);

        this.id = id;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.order = order;


    }

    public static void validateConstraints(Integer order) {
        final List<DomainErrorField> errors = new ArrayList<>();

        if (order == null) {
            errors.add(new DomainErrorField("order", "Order can not be null"));
        }

        if (order != null && order < 0 ) {
            errors.add(new DomainErrorField("order", "Order can not be negative."));
        }

        if (!errors.isEmpty()) {
            throw new DomainValidationException(errors);
        }
    }
}
