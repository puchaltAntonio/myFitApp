package com.learning.myfitapp.modules.workout.application.usecases.workout.deleteworkout;

import com.learning.myfitapp.modules.workout.application.exceptions.InvalidWorkoutOwnerException;


public interface DeleteWorkoutUseCase {
    void deleteWorkout(final DeleteWorkoutUseCaseRequest request) throws InvalidWorkoutOwnerException;
}
