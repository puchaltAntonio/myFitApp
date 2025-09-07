package com.learning.myfitapp.common.clients.keycloak;

import com.learning.myfitapp.common.clients.AuthAdminClient;
import com.learning.myfitapp.common.config.KeycloakConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakAdminClient implements AuthAdminClient {

    private final List<String> actionEmailList = List.of("UPDATE_PASSWORD");

    private final KeycloakConfig keycloakConfig;

    @Override
    public Void sendRecoverPasswordEmail(String email) {
        getUserResourceByEmail(email)
                .ifPresent(userResource ->
                        userResource.executeActionsEmail(actionEmailList));
        return null;
    }

    public Optional<UserResource> getUserResourceByEmail(final String email) {
        final UsersResource usersResource = keycloakConfig.getUsersResource();
        final List<UserRepresentation> userRepresentations = usersResource.searchByEmail(email, true);
        if(!userRepresentations.isEmpty()) {
            final UserRepresentation userRepresentation = userRepresentations.getFirst();
            return Optional.ofNullable(usersResource.get(userRepresentation.getId()));
        }
        return Optional.empty();
    }

    public Optional<String> createUser(final UserRepresentation user){
        final UsersResource usersResource = keycloakConfig.getUsersResource();
        final Response response = usersResource.create(user);
        final Response.StatusType responseStatusInfo = response.getStatusInfo();

        if(Response.Status.CONFLICT.equals(responseStatusInfo)) {
            return Optional.empty();
        } else if (!Response.Status.CREATED.equals(responseStatusInfo)) {
            log.warn(
                    "Unexpected result while trying to create use, status: {}, reason: {}",
                    responseStatusInfo.getStatusCode(),
                    responseStatusInfo.getReasonPhrase()
            );
            return Optional.empty();
        }
        return  Optional.of(CreatedResponseUtil.getCreatedId(response));
    }
}
