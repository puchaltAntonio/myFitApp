package com.learning.myfitapp.modules.profile.infrastructure.rest.controllers;


import com.learning.myfitapp.common.audit.AuditorAwareImpl;
import com.learning.myfitapp.common.exceptions.UnauthorizedException;

import com.learning.myfitapp.modules.profile.application.usecases.deleteprofile.DeleteProfileUseCase;
import com.learning.myfitapp.modules.profile.application.usecases.getprofile.GetProfileUseCase;
import com.learning.myfitapp.modules.profile.application.usecases.getprofile.GetProfileUseCaseRequest;
import com.learning.myfitapp.modules.profile.infrastructure.rest.mappers.ProfileRestMapper;
import com.learning.myfitapp.modules.profile.infrastructure.rest.responses.ProfileRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = ProfileRestController.SWAGGER_TAG)
@RequestMapping(path = ProfileRestController.CONTROLLER_PATH )
@Validated
public class ProfileRestController {

    public static final String SWAGGER_TAG = "Profile API";

    public static final String CONTROLLER_PATH = "/v1/profile";

    public static final String GET_DELETE_AND_UPDATE_PROFILE_PATH = "/{id}";

    private final GetProfileUseCase getProfileUseCase;

    private final DeleteProfileUseCase deleteProfileUseCase;

    private final AuditorAwareImpl auditorAwareImpl;

    private static final ProfileRestMapper PROFILE_REST_MAPPER = ProfileRestMapper.INSTANCE;

    @GetMapping(path = GET_DELETE_AND_UPDATE_PROFILE_PATH)
    @Operation(summary = "Get public profile data by id")
    public ResponseEntity<ProfileRestResponse> getProfileById(
            @PathVariable final UUID id
    ) {
        final ProfileRestResponse response = PROFILE_REST_MAPPER.profileToResponse(
                getProfileUseCase.getProfile(
                        new GetProfileUseCaseRequest(id)
                )
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get authenticated user data")
    public ResponseEntity<ProfileRestResponse> getAccount() {
        //TODO:Create AccountRestResponse
        final ProfileRestResponse response = PROFILE_REST_MAPPER.profileToResponse(
                getProfileUseCase
                        .getProfile(
                                new GetProfileUseCaseRequest(
                                        auditorAwareImpl.getToken().orElseThrow(UnauthorizedException::new)
                                )
                        )
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = GET_DELETE_AND_UPDATE_PROFILE_PATH)
    @Operation(summary = "Delete profile by id")
    public ResponseEntity<ProfileRestResponse> deleteProfileById(
            @PathVariable final UUID id
    ) {
        deleteProfileUseCase.deleteProfile(id);

        return ResponseEntity.noContent().build();
    }

}
