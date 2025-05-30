package com.learning.myfitapp.modules.exercise.application.usecases.getexercise;

import com.learning.myfitapp.modules.exercise.application.exceptions.ExerciseNotFoundException;
import com.learning.myfitapp.modules.exercise.application.repositories.ExerciseRepository;
import com.learning.myfitapp.modules.exercise.domain.models.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GetExerciseUseCaseImpl implements GetExerciseUseCase{

    private final ExerciseRepository repository;

    @Override
    public Exercise getExercise(UUID id) throws ExerciseNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ExerciseNotFoundException(id)
                );
    }
}
