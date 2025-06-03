package com.learning.myfitapp.modules.workout.infrastructure.persistance.mappers;

import com.learning.myfitapp.modules.workout.domain.models.Workout;
import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutJpaMapper {
    WorkoutJpaMapper INSTANCE = Mappers.getMapper(WorkoutJpaMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "exercises", source = "workoutExercises")
    @Mapping(target = "profile.id", source = "profileId")
    WorkoutJpaEntity domainToJpa(final Workout workout);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "workoutExercises", source = "exercises")
    @Mapping(target = "profileId", source = "profile.id")
    Workout jpaToDomain(final WorkoutJpaEntity workout);
}
