package com.learning.myfitapp.common.audit;

import com.learning.myfitapp.common.exceptions.UnauthorizedException;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return getToken()
                .map(JwtAuthenticationToken::getName);
    }

    public Optional<JwtAuthenticationToken> getToken() {
        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if(authentication == null) {
            return Optional.empty();
        }

        return Optional.of((JwtAuthenticationToken) authentication);
    }

    public JwtAuthenticationToken getTokenOrThrowError() {
        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if(authentication == null) {
            throw new UnauthorizedException();
        }
        return (JwtAuthenticationToken) authentication;
    }

    public UUID getAuthIdOrThrowError() { return UUID.fromString(getTokenOrThrowError().getName()); }
}
