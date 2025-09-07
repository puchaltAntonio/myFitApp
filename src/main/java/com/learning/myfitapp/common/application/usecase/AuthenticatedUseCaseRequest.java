package com.learning.myfitapp.common.application.usecase;

import com.learning.myfitapp.modules.profile.application.usecases.getprofile.GetProfileUseCaseRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Getter
@Setter
@EqualsAndHashCode
public abstract class AuthenticatedUseCaseRequest {
    protected GetProfileUseCaseRequest getProfileUseCaseRequest;

    protected AuthenticatedUseCaseRequest(@NonNull final JwtAuthenticationToken jwtToken) {
        this.getProfileUseCaseRequest = new GetProfileUseCaseRequest(jwtToken);
    }
}
