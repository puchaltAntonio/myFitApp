package com.learning.myfitapp.modules.profile.infrastructure.seeders;

import com.learning.myfitapp.MyfitappApplication;
import com.learning.myfitapp.common.clients.keycloak.KeycloakAdminClient;
import com.learning.myfitapp.modules.profile.infrastructure.persistance.entities.ProfileJpaEntity;
import com.learning.myfitapp.modules.profile.infrastructure.persistance.repositories.jpa.ProfileJpaRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
@Getter
@Profile(MyfitappApplication.DEV_PROFILE)
public class ProfileMockSeeder {
    private final List<UUID> profileIds = new ArrayList<>();

    private final ProfileJpaRepository repository;

    private final KeycloakAdminClient keycloakAdminClient;

    private static UUID getIdFromUserResource(UserResource userResource) {
        return UUID.fromString(userResource.toRepresentation().getId());
    }

    public void createData() {
        log.info("Creating Profile Mock Data...");
        for (ProfileJpaEntity profile : ProfileMockData.dbData) {
            final UUID id = keycloakAdminClient.getUserResourceByEmail(profile.getEmail())
                    .map(ProfileMockSeeder::getIdFromUserResource).orElseGet(
                            () -> UUID.fromString(
                                    keycloakAdminClient.createUser(
                                            ProfileMockData.createUserRepresentation(
                                                    profile.getEmail(), profile.getName(), profile.getSurname(),"1234"
                                            )
                                    ).orElseThrow()
                            )
                    );
            if (!repository.existsById(id)){
                profile.setId(id);
                repository.save(profile);
            }

            profileIds.add(id);
        }
    }
}
