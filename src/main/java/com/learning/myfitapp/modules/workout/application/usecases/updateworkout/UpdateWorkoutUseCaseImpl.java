package com.learning.myfitapp.modules.workout.application.usecases.updateworkout;

import com.learning.myfitapp.modules.profile.application.usecases.getprofile.GetProfileUseCase;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import com.learning.myfitapp.modules.workout.application.usecases.createworkoutexerciseusecase.CreateWorkoutExerciseUseCase;
import com.learning.myfitapp.modules.workout.application.usecases.createworkoutexerciseusecase.CreateWorkoutExerciseUseCaseRequest;
import com.learning.myfitapp.modules.workout.domain.models.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateWorkoutUseCaseImpl implements UpdateWorkoutUseCase{

    private final WorkoutRepository workoutRepository;
    private final GetProfileUseCase getProfileUseCase;
    private final CreateWorkoutExerciseUseCase createWorkoutExerciseUseCase;

    @Override
    public Workout updateWorkout(UpdateWorkoutUseCaseRequest request) {
        getProfileUseCase.getProfile(request.getProfileId());


        Workout savedWorkout = workoutRepository.save(
                new Workout(
                        null,
                        request.getName(),
                        request.getProfileId()
                )
        );

        List<UUID> exercisesIds = request.getWorkoutExercisesIds();

        IntStream.range(0, exercisesIds.size()).forEach(i -> {
            UUID id = exercisesIds.get(i);
            CreateWorkoutExerciseUseCaseRequest useCaseRequest = new CreateWorkoutExerciseUseCaseRequest();
            useCaseRequest.setWorkoutId(savedWorkout.getId());
            useCaseRequest.setExerciseId(id);
            useCaseRequest.setOrder(i+1);
            createWorkoutExerciseUseCase.createWorkoutExercise(useCaseRequest);
        });




    }
}
