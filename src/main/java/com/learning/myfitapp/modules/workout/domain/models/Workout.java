package com.learning.myfitapp.modules.workout.domain.models;

import com.learning.myfitapp.common.domain.exception.DomainErrorField;
import com.learning.myfitapp.common.domain.exception.DomainValidationException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Workout {

    private UUID id;

    private String name;

    private UUID profileId;


    public Workout(UUID id, String name, UUID profileId) {

        validateConstraints(
                name
        );

        this.id = id;
        this.name = name;
        this.profileId = profileId;
    }

    public static void validateConstraints(String name) {
        final List<DomainErrorField> errors = new ArrayList<>();

        if (name == null || name.isBlank()) {
            errors.add(new DomainErrorField("name", "Name can not be blank."));
        }
        if (name != null && name.length() > 60) {
            errors.add(new DomainErrorField("name", "Name's max length is 60 characters."));
        }

        if (!errors.isEmpty()) {
            throw new DomainValidationException(errors);
        }
    }
}
