package com.learning.myfitapp.modules.auth.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AccessRequest(
        @NotNull
        @Email
        @Schema(example = "user@mail.com")
        String email,
        @NotBlank
        @Size(max = 300)
        @Schema(example = "password")
        String password
) {
}
