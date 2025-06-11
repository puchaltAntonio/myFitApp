package com.learning.myfitapp.modules.workout.application.usecases.deleteworkout;

import com.learning.myfitapp.modules.exercise.application.exceptions.ExerciseNotFoundException;
import com.learning.myfitapp.modules.workout.application.exceptions.WorkoutNotFoundException;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteWorkoutUseCaseImpl implements DeleteWorkoutUseCase{

    private final WorkoutRepository repository;

    @Override
    public void deleteWorkout(UUID id) throws WorkoutNotFoundException {
        if(!repository.existsById(id)){
            throw new ExerciseNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
