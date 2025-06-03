package com.learning.myfitapp.modules.profile.infrastructure.rest.controllers;


import com.learning.myfitapp.modules.profile.application.usecases.createprofile.CreateProfileUseCase;
import com.learning.myfitapp.modules.profile.application.usecases.createprofile.CreateProfileUseCaseRequest;
import com.learning.myfitapp.modules.profile.application.usecases.deleteprofile.DeleteProfileUseCase;
import com.learning.myfitapp.modules.profile.application.usecases.getprofile.GetProfileUseCase;
import com.learning.myfitapp.modules.profile.infrastructure.rest.mappers.ProfileRestMapper;
import com.learning.myfitapp.modules.profile.infrastructure.rest.requests.ProfileRestRequest;
import com.learning.myfitapp.modules.profile.infrastructure.rest.responses.ProfileRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = ProfileRestController.SWAGGER_TAG)
@RequestMapping(path = ProfileRestController.CONTROLLER_PATH )
//@Validated
public class ProfileRestController {

    public static final String SWAGGER_TAG = "Profile API";

    public static final String CONTROLLER_PATH = "/v1/profile";

    public static final String GET_DELETE_AND_UPDATE_PROFILE_PATH = "/{id}";

    private final CreateProfileUseCase createProfileUseCase;

    private final GetProfileUseCase getProfileUseCase;

    private final DeleteProfileUseCase deleteProfileUseCase;

    private static final ProfileRestMapper PROFILE_REST_MAPPER = ProfileRestMapper.INSTANCE;

    @PostMapping
    @Operation(summary = "Create a profile")
    public ResponseEntity<ProfileRestResponse> createProfile(
            @Valid @RequestBody final ProfileRestRequest request
    ) {
        final CreateProfileUseCaseRequest useCaseRequest = new CreateProfileUseCaseRequest();

        useCaseRequest.setUsername(request.username());

        final ProfileRestResponse response = PROFILE_REST_MAPPER.profileToResponse(
                createProfileUseCase.createProfile(useCaseRequest)
        );

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(
                location
        ).body(
                response
        );
    }

    @GetMapping(path = GET_DELETE_AND_UPDATE_PROFILE_PATH)
    @Operation(summary = "Get a profile by id")
    public ResponseEntity<ProfileRestResponse> getProfileById(
            @PathVariable final UUID id
    ) {
        final ProfileRestResponse response = PROFILE_REST_MAPPER.profileToResponse(
                getProfileUseCase.getProfile(id)
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
