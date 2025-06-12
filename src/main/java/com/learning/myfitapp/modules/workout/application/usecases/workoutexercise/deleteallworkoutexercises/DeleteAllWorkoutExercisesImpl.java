package com.learning.myfitapp.modules.workout.application.usecases.workoutexercise.deleteallworkoutexercises;

import com.learning.myfitapp.modules.workout.application.repositories.WorkoutExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteAllWorkoutExercisesImpl implements DeleteAllWorkoutExercises{

    private final WorkoutExerciseRepository repository;

    @Override
    public void deleteAllWorkoutExercises(UUID id) {
        repository.deleteAllByWorkoutId(id);
    }
}
