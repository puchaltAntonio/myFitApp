package com.learning.myfitapp.modules.workout.application.usecases.workoutexercise.createworkoutexerciseusecase;

import com.learning.myfitapp.modules.workout.application.repositories.WorkoutExerciseRepository;
import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateWorkoutExerciseUseCaseImpl implements CreateWorkoutExerciseUseCase{

    private final WorkoutExerciseRepository repository;

    @Override
    public WorkoutExercise createWorkoutExercise(CreateWorkoutExerciseUseCaseRequest request) {

        return repository.save(
                new WorkoutExercise(
                        null,
                        request.getWorkoutId(),
                        request.getExerciseId(),
                        request.getOrder()
                )
        );
    }
}
