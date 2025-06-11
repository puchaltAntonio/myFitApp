package com.learning.myfitapp.modules.workout.application.usecases.getworkout;

import com.learning.myfitapp.modules.workout.application.exceptions.WorkoutNotFoundException;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import com.learning.myfitapp.modules.workout.domain.models.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GetWorkoutUseCaseImpl implements GetWorkoutUseCase{

    private final WorkoutRepository workoutRepository;

    @Override
    public Workout getWorkout(UUID id) throws WorkoutNotFoundException {
        return workoutRepository.findById(id).orElseThrow(
                () -> new WorkoutNotFoundException(id)
        );
    }
}
