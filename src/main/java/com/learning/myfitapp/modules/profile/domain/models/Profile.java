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

    private String name;
    
    private String surname;
    
    private String email;


    public Profile(UUID id,
                   String name,
                   String surname,
                   String email
    ) {

        validateConstraints(name, surname);

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public static void validateConstraints(String name, String surname){
        final List<DomainErrorField> errors = new ArrayList<>();

        if (name == null || name.isBlank()) {
            errors.add(new DomainErrorField("name", "name can not be blank."));
        }
        if (name != null && name.length() > 60) {
            errors.add(new DomainErrorField("name", "name's max length is 60 characters."));
        }

        if (surname == null || surname.isBlank()) {
            errors.add(new DomainErrorField("surname", "surname can not be blank."));
        }
        if (surname != null && surname.length() > 60) {
            errors.add(new DomainErrorField("surname", "surname's max length is 60 characters."));
        }

        //TODO: Check email?

        if (!errors.isEmpty()) {
            throw new DomainValidationException(errors);
        }
    }

}
