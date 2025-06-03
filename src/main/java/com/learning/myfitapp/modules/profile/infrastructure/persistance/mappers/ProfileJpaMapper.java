package com.learning.myfitapp.modules.profile.infrastructure.persistance.mappers;

import com.learning.myfitapp.modules.profile.domain.models.Profile;
import com.learning.myfitapp.modules.profile.infrastructure.persistance.entities.ProfileJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileJpaMapper {
    ProfileJpaMapper INSTANCE = Mappers.getMapper(ProfileJpaMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "workouts", ignore = true)
    ProfileJpaEntity profileToJpa(final Profile profile);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    Profile jpaToProfile(final ProfileJpaEntity profileJpaEntity);

}
