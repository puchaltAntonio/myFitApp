package com.learning.myfitapp.modules.auth.models.mappers;

import com.learning.myfitapp.modules.auth.models.dtos.TokensDto;
import com.learning.myfitapp.modules.auth.models.responses.TokensResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TokensMapper {
    TokensMapper INSTANCE = Mappers.getMapper(TokensMapper.class);

    TokensResponse tokensDtoResponse(
            final TokensDto tokensDto
    );
}
