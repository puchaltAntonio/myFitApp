package com.learning.myfitapp.modules.exercise.infrastructure.rest.controllers;

import com.learning.myfitapp.modules.exercise.application.usecases.createexercise.CreateExerciseUseCase;
import com.learning.myfitapp.modules.exercise.application.usecases.createexercise.CreateExerciseUseCaseRequest;
import com.learning.myfitapp.modules.exercise.application.usecases.getexercise.GetExerciseUseCase;
import com.learning.myfitapp.modules.exercise.infrastructure.rest.mappers.ExerciseRestMapper;
import com.learning.myfitapp.modules.exercise.infrastructure.rest.requests.ExerciseRestRequest;
import com.learning.myfitapp.modules.exercise.infrastructure.rest.responses.ExerciseRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = ExerciseRestController.SWAGGER_TAG)
@RequestMapping(path = ExerciseRestController.CONTROLLER_PATH )
//@Validated
public class ExerciseRestController {

    public static final String SWAGGER_TAG = "Exercise API";

    public static final String CONTROLLER_PATH = "/v1/exercise";

    public static final String GET_DELETE_AND_UPDATE_EXERCISE_PATH = "/{id}";

    private final CreateExerciseUseCase createExerciseUseCase;

    private final GetExerciseUseCase getExerciseUseCase;

    private static final ExerciseRestMapper EXERCISE_REST_MAPPER = ExerciseRestMapper.INSTANCE;

    @PostMapping
    @Operation(summary = "Create a exercise")
    public ResponseEntity<ExerciseRestResponse> createExercise(
          @Valid @RequestBody final ExerciseRestRequest request
    ) {
            final CreateExerciseUseCaseRequest useCaseRequest = new CreateExerciseUseCaseRequest();

            useCaseRequest.setName(request.name());
            useCaseRequest.setPrimaryMuscle(request.primaryMuscle());
            useCaseRequest.setSecondaryMuscle(request.secondaryMuscle());
            useCaseRequest.setEquipment(request.equipment());

            final ExerciseRestResponse response = EXERCISE_REST_MAPPER.exerciseToResponse(
                    createExerciseUseCase.createExercise(useCaseRequest)
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

    @GetMapping(path = GET_DELETE_AND_UPDATE_EXERCISE_PATH)
    @Operation(summary = "Get a exercise by id")
    public ResponseEntity<ExerciseRestResponse> getExerciseById(
          @PathVariable final UUID id
    ) {
        final ExerciseRestResponse response = EXERCISE_REST_MAPPER.exerciseToResponse(
                getExerciseUseCase.getExercise(id)
        );

        return ResponseEntity.ok(response);
    }


}
