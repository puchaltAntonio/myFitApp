package com.learning.myfitapp.modules.workout.application.usecases.workout.getworkout;

import com.learning.myfitapp.modules.workout.application.exceptions.WorkoutNotFoundException;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import com.learning.myfitapp.modules.workout.application.usecases.workoutexercise.getallworkoutexercisesbyworkoutid.GetAllWorkoutExercisesByWorkoutIdUseCase;
import com.learning.myfitapp.modules.workout.domain.models.Workout;
import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GetWorkoutUseCaseImpl implements GetWorkoutUseCase{

    private final WorkoutRepository workoutRepository;
    private final GetAllWorkoutExercisesByWorkoutIdUseCase getAllWorkoutExercisesByWorkoutIdUseCase;


    @Override
    public Workout getWorkout(UUID id) throws WorkoutNotFoundException {
        Workout workout = workoutRepository.findById(id).orElseThrow(
                () -> new WorkoutNotFoundException(id)
        );

        workout.setWorkoutExerciseList(
                getAllWorkoutExercisesByWorkoutIdUseCase
                        .getAllWorkoutExerciseByWorkoutId(id)
                        .stream()
                        .map(WorkoutExercise::getId).toList());

        return workout;
    }
}
