package com.learning.myfitapp.modules.workout.infrastructure.persistance.mappers;

import com.learning.myfitapp.modules.workout.domain.models.WorkoutExercise;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutExerciseJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutExerciseJpaMapper {
    WorkoutExerciseJpaMapper INSTANCE = Mappers.getMapper(WorkoutExerciseJpaMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "exercise.id", source = "exerciseId")
    @Mapping(target = "workout.id", source = "workoutId")
    @Mapping(target = "order", source = "order")
    WorkoutExerciseJpaEntity domainToJpa(final WorkoutExercise workoutExercise);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "exerciseId", source = "exercise.id")
    @Mapping(target = "workoutId", source = "workout.id")
    @Mapping(target = "order", source = "order")
    WorkoutExercise jpaToDomain(final WorkoutExerciseJpaEntity workoutExerciseJpaEntity);
}
