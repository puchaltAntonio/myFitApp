package com.learning.myfitapp.modules.exercise.infrastructure.persistance.mappers;

import com.learning.myfitapp.modules.exercise.domain.models.Exercise;
import com.learning.myfitapp.modules.exercise.infrastructure.persistance.entities.ExerciseJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseJpaMapper {
    ExerciseJpaMapper INSTANCE = Mappers.getMapper(ExerciseJpaMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "primaryMuscle", source = "primaryMuscle")
    @Mapping(target = "secondaryMuscle", source = "secondaryMuscle")
    @Mapping(target = "equipment", source = "equipment")
    @Mapping(target = "animation", ignore = true)
    ExerciseJpaEntity exerciseToJpa(final Exercise exercise);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "primaryMuscle", source = "primaryMuscle")
    @Mapping(target = "secondaryMuscle", source = "secondaryMuscle")
    @Mapping(target = "equipment", source = "equipment")
    @Mapping(target = "animation", ignore = true)
    Exercise jpaToExercise(final ExerciseJpaEntity exerciseJpaEntity);
}
