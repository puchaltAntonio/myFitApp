package com.learning.myfitapp.modules.exercise.application.usecases.createexercise;

import com.learning.myfitapp.modules.exercise.application.repositories.ExerciseRepository;
import com.learning.myfitapp.modules.exercise.domain.models.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateExerciseUseCaseImpl implements CreateExerciseUseCase{

    private final ExerciseRepository exerciseRepository;

    @Override
    public Exercise createExercise(CreateExerciseUseCaseRequest request) {

        final Exercise exercise = new Exercise(
                null,
                request.getName(),
                request.getPrimaryMuscle(),
                request.getSecondaryMuscle(),
                request.getEquipment()
        );
        return exerciseRepository.save(exercise);
    }
}
