package com.learning.myfitapp.modules.auth.models.responses;

import lombok.Builder;

@Builder
public record TokensResponse(
        String accessToken,
        String refreshToken,
        long accessExpiresIn,
        long refreshExpiresIn) {
}
