package com.learning.myfitapp.modules.auth.services;

import com.learning.myfitapp.modules.auth.models.requests.AccessRequest;
import com.learning.myfitapp.modules.auth.models.responses.TokensResponse;

public interface AuthService {
    TokensResponse access(final AccessRequest accessRequest);
    TokensResponse refresh(final String refreshToken);
    Void logout(final String refreshToken);
    Void sendRecoverPasswordEmail(final String email);
}
