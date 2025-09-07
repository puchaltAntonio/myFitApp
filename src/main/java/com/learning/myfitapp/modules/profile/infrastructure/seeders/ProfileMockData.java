package com.learning.myfitapp.modules.profile.infrastructure.seeders;

import com.learning.myfitapp.modules.profile.infrastructure.persistance.entities.ProfileJpaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileMockData {

    private static String name = "Toni";
    private static String surname = "Puchalt";
    private static String email = "toni@mail.com";
    private static String password = "1234";
    private static String name2 = "Laura";
    private static String surname2 = "Andrés";
    private static String email2 = "laura@mail.com";




    public static final List<ProfileJpaEntity> dbData = List.of(
            new ProfileJpaEntity(
                    null,
                    name,
                    surname,
                    email,
                    Collections.emptyList()
            ),
            new ProfileJpaEntity(
                    null,
                    name2,
                    surname2,
                    email2,
                    Collections.emptyList()
            )
    );

    public static final List<UserRepresentation> authData = List.of(
            createUserRepresentation(
                email,
                name,
                surname,
                password
            ),
            createUserRepresentation(
                email2,
                name2,
                surname2,
                password
            )
    );

    public static UserRepresentation createUserRepresentation(
            final String email,
            final String name,
            final String surname,
            final String password
    ) {
        final CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);

        final UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(email);
        userRepresentation.setUsername(email);
        userRepresentation.setFirstName(name);
        userRepresentation.setLastName(surname);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        return userRepresentation;
    }
}
