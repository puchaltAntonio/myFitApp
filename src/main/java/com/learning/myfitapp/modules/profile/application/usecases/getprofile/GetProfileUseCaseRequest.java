package com.learning.myfitapp.modules.profile.application.usecases.getprofile;


import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class GetProfileUseCaseRequest {

    public static final String TOKEN_EMAIL_ATTRIBUTE = "email";
    public static final String TOKEN_NAME_ATTRIBUTE = "given_name";
    public static final String TOKEN_SURNAME_ATTRIBUTE = "family_name";

    private UUID id;
    private String email;
    private String name;
    private String surname;
    private Boolean createIfNoExists;

    public GetProfileUseCaseRequest(@NonNull final JwtAuthenticationToken jwtToken) {
        this.id = UUID.fromString(jwtToken.getName());
        final Map<String, Object> tokenAttributes = jwtToken
                .getTokenAttributes();
        this.email = tokenAttributes.get(TOKEN_EMAIL_ATTRIBUTE).toString();
        this.name = tokenAttributes
                        .get(TOKEN_NAME_ATTRIBUTE) != null ?
                tokenAttributes
                        .get(TOKEN_NAME_ATTRIBUTE).toString()
                        : "";
        this.surname = tokenAttributes
                        .get(TOKEN_SURNAME_ATTRIBUTE) != null ?
                tokenAttributes
                        .get(TOKEN_SURNAME_ATTRIBUTE).toString()
                        : "";
        this.createIfNoExists = true;

    }

    public GetProfileUseCaseRequest(@NonNull final UUID id){
        this.id = id;
        this.createIfNoExists = false;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetProfileUseCaseRequest that = (GetProfileUseCaseRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) &&
               Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, surname);
    }
}
