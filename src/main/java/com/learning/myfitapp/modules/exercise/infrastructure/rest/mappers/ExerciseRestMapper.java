package com.learning.myfitapp.modules.exercise.infrastructure.rest.mappers;

import com.learning.myfitapp.modules.exercise.domain.models.Exercise;
import com.learning.myfitapp.modules.exercise.infrastructure.rest.responses.ExerciseRestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseRestMapper {
    ExerciseRestMapper INSTANCE = Mappers.getMapper(ExerciseRestMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "primaryMuscle", source = "primaryMuscle")
    @Mapping(target = "secondaryMuscle", source = "secondaryMuscle")
    @Mapping(target = "equipment", source = "equipment")
    ExerciseRestResponse exerciseToResponse(final Exercise exercise);
}
