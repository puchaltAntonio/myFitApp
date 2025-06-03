package com.learning.myfitapp.modules.profile.infrastructure.rest.mappers;

import com.learning.myfitapp.modules.profile.domain.models.Profile;
import com.learning.myfitapp.modules.profile.infrastructure.rest.responses.ProfileRestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileRestMapper {
    ProfileRestMapper INSTANCE = Mappers.getMapper(ProfileRestMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    ProfileRestResponse profileToResponse(final Profile profile);
}
