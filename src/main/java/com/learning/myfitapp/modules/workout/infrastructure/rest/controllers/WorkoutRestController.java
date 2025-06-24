package com.learning.myfitapp.modules.workout.infrastructure.rest.controllers;


import com.learning.myfitapp.common.audit.AuditorAwareImpl;
import com.learning.myfitapp.modules.workout.application.usecases.workout.createworkout.CreateWorkoutUseCase;
import com.learning.myfitapp.modules.workout.application.usecases.workout.createworkout.CreateWorkoutUseCaseRequest;
import com.learning.myfitapp.modules.workout.application.usecases.workout.deleteworkout.DeleteWorkoutUseCase;
import com.learning.myfitapp.modules.workout.application.usecases.workout.deleteworkout.DeleteWorkoutUseCaseRequest;
import com.learning.myfitapp.modules.workout.application.usecases.workout.getworkout.GetWorkoutUseCase;
import com.learning.myfitapp.modules.workout.application.usecases.workout.updateworkout.UpdateWorkoutUseCase;
import com.learning.myfitapp.modules.workout.application.usecases.workout.updateworkout.UpdateWorkoutUseCaseRequest;
import com.learning.myfitapp.modules.workout.infrastructure.rest.mappers.WorkoutRestMapper;
import com.learning.myfitapp.modules.workout.infrastructure.rest.requests.WorkoutRestRequest;
import com.learning.myfitapp.modules.workout.infrastructure.rest.responses.WorkoutRestResponse;
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
@Tag(name = WorkoutRestController.SWAGGER_TAG)
@RequestMapping(path = WorkoutRestController.CONTROLLER_PATH )
public class WorkoutRestController {

    public static final String SWAGGER_TAG = "Workout API";

    public static final String CONTROLLER_PATH = "/v1/workout/";

    public static final String GET_DELETE_AND_UPDATE_WORKOUT_PATH = "/{id}";

    private static final WorkoutRestMapper WORKOUT_REST_MAPPER = WorkoutRestMapper.INSTANCE;

    private final CreateWorkoutUseCase createWorkoutUseCase;
    private final DeleteWorkoutUseCase deleteWorkoutUseCase;
    private final GetWorkoutUseCase getWorkoutUseCase;
    private final UpdateWorkoutUseCase updateWorkoutUseCase;
    private final AuditorAwareImpl auditorAwareImpl;


    @PostMapping
    @Operation(summary = "Create a workout")
    public ResponseEntity<WorkoutRestResponse> createWorkout(
            @Valid @RequestBody final WorkoutRestRequest request
    ) {
        final CreateWorkoutUseCaseRequest useCaseRequest = new CreateWorkoutUseCaseRequest(
                auditorAwareImpl.getTokenOrThrowError(),
                request.name(),
                request.exercisesIds()
        );
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

    @DeleteMapping(path = GET_DELETE_AND_UPDATE_WORKOUT_PATH)
    @Operation(summary = "Delete workout by id")
    public ResponseEntity<WorkoutRestResponse> deleteWorkoutById(
            @PathVariable final UUID id
    ) {
        DeleteWorkoutUseCaseRequest request = new DeleteWorkoutUseCaseRequest(
                auditorAwareImpl.getTokenOrThrowError(),
                id
        );

        deleteWorkoutUseCase.deleteWorkout(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = GET_DELETE_AND_UPDATE_WORKOUT_PATH)
    @Operation(summary = "Get workout by id")
    public ResponseEntity<WorkoutRestResponse> getWorkoutById(
            @PathVariable final UUID id
    ) {
        WorkoutRestResponse response = WORKOUT_REST_MAPPER.workoutToResponse(
                getWorkoutUseCase.getWorkout(id)
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = GET_DELETE_AND_UPDATE_WORKOUT_PATH)
    @Operation(summary = "Update workout by id")
    public ResponseEntity<WorkoutRestResponse> updateWorkoutById(
            @PathVariable final UUID id, @RequestBody final WorkoutRestRequest request
    ) {

        UpdateWorkoutUseCaseRequest updateWorkoutUseCaseRequest = new UpdateWorkoutUseCaseRequest(
                auditorAwareImpl.getTokenOrThrowError(),
                request.name(),
                request.exercisesIds(),
                id
        );

        WorkoutRestResponse response = WORKOUT_REST_MAPPER.workoutToResponse(
                updateWorkoutUseCase.updateWorkout(updateWorkoutUseCaseRequest)
        );
        return ResponseEntity.ok(response);
    }
}
