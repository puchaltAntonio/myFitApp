package com.learning.myfitapp.modules.exercise.application.usecases.createexercise;

import com.learning.myfitapp.modules.exercise.domain.models.EquipmentEnum;
import com.learning.myfitapp.modules.exercise.domain.models.Exercise;
import com.learning.myfitapp.modules.exercise.domain.models.MuscleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateExerciseUseCaseImpl implements CreateExerciseUseCase{
    @Override
    public Exercise createExercise(CreateExerciseUseCaseRequest createExerciseUseCaseRequest) {
        return new Exercise(
                UUID.randomUUID(),
                "Ejercicio 1",
                MuscleEnum.ABDOMINALS,
                MuscleEnum.ADDUCTORS,
                EquipmentEnum.DUMBBELL
                );
    }
}
