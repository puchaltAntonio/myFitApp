package com.learning.myfitapp.common.clients.keycloak;


import com.learning.myfitapp.common.config.KeycloakConfig;
import com.learning.myfitapp.common.exceptions.UnauthorizedException;
import com.learning.myfitapp.modules.auth.models.dtos.TokensDto;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import com.nimbusds.oauth2.sdk.ErrorObject;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.Tokens;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakClient {

    private final KeycloakConfig keycloakConfig;

    @SneakyThrows
    public TokensDto access(
            final String username,
            final String password
    ) {
        try {
            log.info("Requesting access tokens for user: {}", username);

            final TokenResponse tokenResponse = keycloakConfig.getAccessToken(username, password);

            if (tokenResponse.toHTTPResponse().indicatesSuccess()) {
                log.debug("Successfully authorized user {}", username);
                return tokenToJwts(tokenResponse);
            }

            final ErrorObject errorObject = tokenResponse
                    .toErrorResponse()
                    .getErrorObject();

            log.info(
                    "Unauthorized request for user {} : {} {}",
                    username,
                    errorObject.getHTTPStatusCode(),
                    errorObject.getDescription()
            );

            throw new UnauthorizedException(errorObject.getDescription());

        } catch (URISyntaxException | IllegalArgumentException | ParseException | IOException ex) {
            log.info(
                    "Error handling authentication for user {} : {}",
                    username,
                    ex.getMessage()
            );
            throw ex;
        }
    }

    @SneakyThrows
    public TokensDto refresh(final String refreshToken){
        try {
            log.info("Requesting access token for refresh token {}", refreshToken);

            final TokenResponse tokenResponse = keycloakConfig.getNewRefreshToken(refreshToken);

            if(tokenResponse.toHTTPResponse().indicatesSuccess()) {
                log.debug("Successfully refresh access for token {}", refreshToken);
                return tokenToJwts(tokenResponse);
            }

            final ErrorObject errorObject = tokenResponse
                    .toErrorResponse()
                    .getErrorObject();

            log.info(
                    "Unauthorized refresh request: {} {}",
                    errorObject.getHTTPStatusCode(),
                    errorObject.getDescription()
            );

            throw new UnauthorizedException(errorObject.getDescription());
        } catch (URISyntaxException | IllegalArgumentException | ParseException | IOException ex) {
            log.info(
                    "Error handling refresh : {}",
                    ex.getMessage()
            );
            throw ex;
        }
    }

    @SneakyThrows
    public Void logout(final String refreshToken){
        try {
            log.info("Requesting logout for token {}", refreshToken);

            final TokenResponse tokenResponse = keycloakConfig.getLogoutToken(
                    refreshToken
            );

            if(tokenResponse.toHTTPResponse().indicatesSuccess()) {
                log.debug("Successfully logout token {}", refreshToken);
                return null;
            }

            final ErrorObject errorObject = tokenResponse
                    .toErrorResponse()
                    .getErrorObject();

            log.info(
                    "Unauthorized refresh request: {} {}",
                    errorObject.getHTTPStatusCode(),
                    errorObject.getDescription()
            );

            throw new UnauthorizedException(errorObject.getDescription());
        } catch(URISyntaxException | IllegalArgumentException | ParseException | IOException ex) {
            log.info(
                    "Error handling refresh: {}",
                    ex.getMessage()
            );
            throw ex;
        }
    }

    public JsonObject decodeToken(final String token) {
        final String[] chunks = token.split("\\.");
        final Base64.Decoder decoder = Base64.getUrlDecoder();
        final String payload = new String(decoder.decode(chunks[1]), StandardCharsets.UTF_8);

        return JsonParser.parseString(payload).getAsJsonObject();
    }

    private TokensDto tokenToJwts(final TokenResponse tokenResponse) {
        final Tokens tokens = tokenResponse.toSuccessResponse().getTokens();
        final AccessToken accessTokenObj = tokens.getAccessToken();

        final String refreshToken = tokens.getRefreshToken().getValue();
        final long iat = decodeToken(refreshToken).get("iat").getAsLong();
        final long exp = decodeToken(refreshToken).get("exp").getAsLong();

        return TokensDto
                .builder()
                .accessToken(accessTokenObj.getValue())
                .refreshToken(refreshToken)
                .accessExpiresIn(accessTokenObj.getLifetime())
                .refreshExpiresIn(exp - iat)
                .build();
    }

}
