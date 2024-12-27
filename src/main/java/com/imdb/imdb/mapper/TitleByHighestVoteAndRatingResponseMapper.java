package com.imdb.imdb.mapper;

import com.imdb.imdb.dto.TitleByHighestVoteAndRatingResponseDto;
import com.imdb.imdb.dto.TitleBySpecialActorsResponseDto;
import com.imdb.imdb.model.imdb.TitleBasics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitleByHighestVoteAndRatingResponseMapper extends BaseMapper<TitleBasics, TitleByHighestVoteAndRatingResponseDto>{
}
