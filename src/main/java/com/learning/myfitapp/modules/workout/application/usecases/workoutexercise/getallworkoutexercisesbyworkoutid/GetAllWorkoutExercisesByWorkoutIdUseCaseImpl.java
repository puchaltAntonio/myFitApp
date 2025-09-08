package com.learning.myfitapp.modules.workout.application.usecases.workoutexercise.getallworkoutexercisesbyworkoutid;

import com.learning.myfitapp.modules.workout.application.exceptions.WorkoutNotFoundException;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutExerciseRepository;
import com.learning.myfitapp.modules.workout.application.repositories.WorkoutRepository;
import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GetAllWorkoutExercisesByWorkoutIdUseCaseImpl implements GetAllWorkoutExercisesByWorkoutIdUseCase{

    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    @Override
    public List<WorkoutExercise> getAllWorkoutExerciseByWorkoutId(UUID id) {
        if(Boolean.FALSE.equals(workoutRepository.existsById(id))) {
            throw new WorkoutNotFoundException(id);
        }
        return workoutExerciseRepository.findAllByWorkoutId(id);
    }
}
