package com.learning.myfitapp.modules.exercise.application.usecases.deleteexercise;

import com.learning.myfitapp.modules.exercise.application.exceptions.ExerciseNotFoundException;
import com.learning.myfitapp.modules.exercise.application.repositories.ExerciseRepository;
import com.learning.myfitapp.modules.exercise.application.usecases.getexercise.GetExerciseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteExerciseUseCaseImpl implements DeleteExerciseUseCase{

    private final ExerciseRepository exerciseRepository;


    @Override
    public void deleteExercise(UUID id) throws ExerciseNotFoundException{
        if(!exerciseRepository.existsById(id)){
            throw new ExerciseNotFoundException(id);
        }
        exerciseRepository.deleteById(id);
    }
}
