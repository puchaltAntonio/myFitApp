package com.learning.myfitapp.modules.workout.application.usecases.createworkout;

import com.learning.myfitapp.modules.profile.application.repositories.ProfileRepository;
import com.learning.myfitapp.modules.profile.application.usecases.createprofile.CreateProfileUseCase;
import com.learning.myfitapp.modules.profile.application.usecases.getprofile.GetProfileUseCase;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import com.learning.myfitapp.modules.workout.application.usecases.createworkoutexerciseusecase.CreateWorkoutExerciseUseCase;
import com.learning.myfitapp.modules.workout.application.usecases.createworkoutexerciseusecase.CreateWorkoutExerciseUseCaseRequest;
import com.learning.myfitapp.modules.workout.domain.models.Workout;
import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateWorkoutUseCaseImpl implements CreateWorkoutUseCase{

    private final WorkoutRepository workoutRepository;
    private final GetProfileUseCase getProfileUseCase;
    private final CreateWorkoutExerciseUseCase createWorkoutExerciseUseCase;

    @Override
    public Workout createWorkout(CreateWorkoutUseCaseRequest request) {

        getProfileUseCase.getProfile(request.getProfileId());





        Workout savedWorkout = workoutRepository.save(
                new Workout(
                        null,
                        request.getName(),
                        request.getProfileId()
                )
        );

        request.getWorkoutExercises().forEach(
                e -> {
                    CreateWorkoutExerciseUseCaseRequest useCaseRequest = new CreateWorkoutExerciseUseCaseRequest();
                    useCaseRequest.setWorkoutId(savedWorkout.getId());
                    useCaseRequest.setExerciseId(e.getExerciseId());
                    useCaseRequest.setOrder(e.getOrder());
                    createWorkoutExerciseUseCase.createWorkoutExercise(useCaseRequest);}
        );


        return savedWorkout;
    }
}
