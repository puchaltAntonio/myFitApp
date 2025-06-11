package com.learning.myfitapp.modules.workout.infrastructure.rest.mappers;

import com.learning.myfitapp.modules.workout.domain.models.Workout;
import com.learning.myfitapp.modules.workout.infrastructure.rest.responses.WorkoutRestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutRestMapper {
    WorkoutRestMapper INSTANCE = Mappers.getMapper(WorkoutRestMapper.class);

    @Mapping(target = "exercisesIds", ignore = true)
    WorkoutRestResponse workoutToResponse(final Workout workout);
}
