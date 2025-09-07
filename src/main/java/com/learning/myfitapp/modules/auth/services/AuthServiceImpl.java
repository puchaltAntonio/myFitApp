package com.learning.myfitapp.modules.auth.services;

import com.learning.myfitapp.common.clients.AuthAdminClient;
import com.learning.myfitapp.common.clients.keycloak.KeycloakClient;
import com.learning.myfitapp.modules.auth.models.dtos.TokensDto;
import com.learning.myfitapp.modules.auth.models.mappers.TokensMapper;
import com.learning.myfitapp.modules.auth.models.requests.AccessRequest;
import com.learning.myfitapp.modules.auth.models.responses.TokensResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthAdminClient authAdminClient;

    private final KeycloakClient authClient;

    private static final TokensMapper tokensMapper = TokensMapper.INSTANCE;


    @Override
    public TokensResponse access(AccessRequest accessRequest) {
        final TokensDto tokens = authClient.access(
                accessRequest.email(),
                accessRequest.password()
        );

        return tokensMapper.tokensDtoResponse(tokens);
    }

    @Override
    public TokensResponse refresh(String refreshToken) {
        final TokensDto tokens = authClient.refresh(refreshToken);
        return tokensMapper.tokensDtoResponse(tokens);
    }

    @Override
    public Void logout(String refreshToken) {
        return authClient.logout(refreshToken);
    }

    @Override
    public Void sendRecoverPasswordEmail(String email) {
        return authAdminClient.sendRecoverPasswordEmail(email);
    }
}
