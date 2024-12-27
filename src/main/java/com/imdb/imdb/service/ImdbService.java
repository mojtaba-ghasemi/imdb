package com.imdb.imdb.service;

import com.imdb.imdb.dto.TitleByHighestVoteAndRatingResponseDto;
import com.imdb.imdb.dto.TitleBySpecialActorsResponseDto;
import com.imdb.imdb.dto.TitleCrewResponseDto;
import com.imdb.imdb.exception.IMDBException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImdbService {

    /*
    Return all the titles in which both director and writer are the same person and he/she is still alive
     */
    List<TitleCrewResponseDto> getTitleByDirectorsSameWriters() throws IMDBException;

    /*
    Get two actors and return all the titles in which both of them played at
     */
    List<TitleBySpecialActorsResponseDto> getTitleBySpecialActors(String actorId1, String actorId2) throws IMDBException;

    /*
    Get a genre from the user and return best titles on each year for that genre based on
    number of votes and rating
     */
    List<TitleByHighestVoteAndRatingResponseDto> getTitleByHighestVoteAndRating(String genre) throws IMDBException;
}
