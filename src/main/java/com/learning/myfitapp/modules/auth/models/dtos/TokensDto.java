package com.learning.myfitapp.modules.auth.models.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokensDto {
    private String accessToken;

    private String refreshToken;

    private long accessExpiresIn;

    private long refreshExpiresIn;
}
