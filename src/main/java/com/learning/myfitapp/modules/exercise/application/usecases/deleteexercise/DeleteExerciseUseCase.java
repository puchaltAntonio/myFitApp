package com.learning.myfitapp.modules.exercise.application.usecases.deleteexercise;

import com.learning.myfitapp.modules.exercise.application.exceptions.ExerciseNotFoundException;

import java.util.UUID;

public interface DeleteExerciseUseCase {
    void deleteExercise(UUID id) throws ExerciseNotFoundException;
}
