package com.learning.myfitapp.modules.profile.domain.models;

import com.learning.myfitapp.common.domain.exception.DomainErrorField;
import com.learning.myfitapp.common.domain.exception.DomainValidationException;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Profile {

    private UUID id;

    private String username;

    //private List<Workout> workouts;

    public Profile(UUID id, String username) {

        validateConstraints(username);

        this.id = id;
        this.username = username;
    }

    public static void validateConstraints(String username){
        final List<DomainErrorField> errors = new ArrayList<>();

        if (username == null || username.isBlank()) {
            errors.add(new DomainErrorField("username", "Username can not be blank."));
        }
        if (username != null && username.length() > 60) {
            errors.add(new DomainErrorField("username", "Username's max length is 60 characters."));
        }

        if (!errors.isEmpty()) {
            throw new DomainValidationException(errors);
        }
    }

}
