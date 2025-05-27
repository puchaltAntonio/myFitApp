package com.learning.myfitapp.modules.exercise.infrastructure.persistance.repositories;

import com.learning.myfitapp.modules.exercise.application.repositories.ExerciseRepository;
import com.learning.myfitapp.modules.exercise.domain.models.Exercise;
import com.learning.myfitapp.modules.exercise.infrastructure.persistance.entities.ExerciseJpaEntity;
import com.learning.myfitapp.modules.exercise.infrastructure.persistance.mappers.ExerciseJpaMapper;
import com.learning.myfitapp.modules.exercise.infrastructure.persistance.repositories.jpa.ExerciseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseRepositoryImpl implements ExerciseRepository {

    private final ExerciseJpaRepository exerciseJpaRepository;

    private static final ExerciseJpaMapper EXERCISE_JPA_MAPPER = ExerciseJpaMapper.INSTANCE;

    @Override
    public Exercise save(Exercise exercise) {

        final ExerciseJpaEntity exerciseJpaEntity = EXERCISE_JPA_MAPPER.exerciseToJpa(exercise);

        return EXERCISE_JPA_MAPPER.jpaToExercise(
                exerciseJpaRepository.save(exerciseJpaEntity)
        );
    }
}
