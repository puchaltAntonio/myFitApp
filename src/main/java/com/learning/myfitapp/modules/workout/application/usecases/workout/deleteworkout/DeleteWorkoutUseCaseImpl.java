package com.learning.myfitapp.modules.workout.application.usecases.workout.deleteworkout;

import com.learning.myfitapp.modules.profile.application.usecases.getprofile.GetProfileUseCase;
import com.learning.myfitapp.modules.profile.domain.models.Profile;
import com.learning.myfitapp.modules.workout.application.exceptions.InvalidWorkoutOwnerException;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import com.learning.myfitapp.modules.workout.application.usecases.workout.getworkout.GetWorkoutUseCase;
import com.learning.myfitapp.modules.workout.domain.models.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class DeleteWorkoutUseCaseImpl implements DeleteWorkoutUseCase{

    private final WorkoutRepository workoutRepository;
    private final GetProfileUseCase getProfileUseCase;
    private final GetWorkoutUseCase getWorkoutUseCase;


    @Override
    public void deleteWorkout(DeleteWorkoutUseCaseRequest request) throws InvalidWorkoutOwnerException{

        final Profile profile = getProfileUseCase.getProfile(request.getGetProfileUseCaseRequest());

        final Workout workout = getWorkoutUseCase.getWorkout(request.getWorkoutId());

        if(!workout.belongsTo(profile.getId())){
            throw new InvalidWorkoutOwnerException(workout.getId());
        }

        workoutRepository.deleteById(workout.getId());
    }
}
