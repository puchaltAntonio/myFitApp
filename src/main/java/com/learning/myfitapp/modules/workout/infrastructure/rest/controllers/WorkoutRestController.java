package com.learning.myfitapp.modules.workout.infrastructure.rest.controllers;


import com.learning.myfitapp.modules.workout.application.usecases.createworkout.CreateWorkoutUseCase;
import com.learning.myfitapp.modules.workout.application.usecases.createworkout.CreateWorkoutUseCaseRequest;
import com.learning.myfitapp.modules.workout.infrastructure.rest.mappers.WorkoutRestMapper;
import com.learning.myfitapp.modules.workout.infrastructure.rest.requests.WorkoutRestRequest;
import com.learning.myfitapp.modules.workout.infrastructure.rest.responses.WorkoutRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequiredArgsConstructor
@Tag(name = WorkoutRestController.SWAGGER_TAG)
@RequestMapping(path = WorkoutRestController.CONTROLLER_PATH )
public class WorkoutRestController {

    public static final String SWAGGER_TAG = "Workout API";

    public static final String CONTROLLER_PATH = "/v1/workout/";

    public static final String GET_DELETE_AND_UPDATE_EXERCISE_PATH = "/{id}";

    private static final WorkoutRestMapper WORKOUT_REST_MAPPER = WorkoutRestMapper.INSTANCE;

    private final CreateWorkoutUseCase createWorkoutUseCase;


    @PostMapping
    @Operation(summary = "Create a workout")
    public ResponseEntity<WorkoutRestResponse> createWorkout(
            @Valid @RequestBody final WorkoutRestRequest request
    ) {
        final CreateWorkoutUseCaseRequest useCaseRequest = new CreateWorkoutUseCaseRequest();

        useCaseRequest.setName(request.name());
        useCaseRequest.setWorkoutExercisesIds(request.exercisesIds());
        useCaseRequest.setProfileId(request.profileId());

        final WorkoutRestResponse response = WORKOUT_REST_MAPPER.workoutToResponse(
            createWorkoutUseCase.createWorkout(useCaseRequest)
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

}
