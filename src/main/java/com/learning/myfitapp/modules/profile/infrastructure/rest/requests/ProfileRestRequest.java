package com.learning.myfitapp.modules.profile.infrastructure.rest.requests;

import lombok.Builder;

@Builder
public record ProfileRestRequest(
        String name,
        String surname,
        String email
) {
}
