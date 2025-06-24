package com.learning.myfitapp.modules.profile.infrastructure.rest.responses;

import java.util.UUID;

public record ProfileRestResponse(
        UUID id,
        String name,
        String surname,
        String email
) {
}
