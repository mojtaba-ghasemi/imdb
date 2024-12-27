package com.imdb.imdb.service;

import com.imdb.imdb.dto.TitleByHighestVoteAndRatingResponseDto;
import com.imdb.imdb.dto.TitleBySpecialActorsResponseDto;
import com.imdb.imdb.dto.TitleCrewResponseDto;
import com.imdb.imdb.exception.IMDBException;
import com.imdb.imdb.exception.enumuration.BusinessExceptionCode;
import com.imdb.imdb.mapper.TitleByHighestVoteAndRatingResponseMapper;
import com.imdb.imdb.mapper.TitleBySpecialActorsResponseMapper;
import com.imdb.imdb.model.imdb.NameBasics;
import com.imdb.imdb.model.imdb.TitleBasics;
import com.imdb.imdb.model.imdb.TitlePrincipals;
import com.imdb.imdb.model.imdb.TitleRatings;
import com.imdb.imdb.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImdbServiceImpl implements ImdbService {
    @Autowired
    private TitleCrewRepository titleCrewRepository;
    @Autowired
    private NameBasicsRepository nameBasicsRepository;
    @Autowired
    private TitlePrincipalsRepository titlePrincipalsRepository;
    @Autowired
    private TitleBasicRepository titleBasicRepository;
    private TitleRatingsRepository titleRatingsRepository;
    private final TitleBySpecialActorsResponseMapper titleBySpecialActorsResponseMapper;
    private final TitleByHighestVoteAndRatingResponseMapper titleByHighestVoteAndRatingResponseMapper;

    public List<TitleCrewResponseDto> getTitleByDirectorsSameWriters() throws IMDBException{
        List<TitleCrewResponseDto> responseDtos = new ArrayList<>();

        titleCrewRepository.findByDirectorsSameWriters().forEach(titleCrew -> {

            Set<String> directorSet = new HashSet<>(titleCrew.getDirectors());
            Set<String> writerSet = new HashSet<>(titleCrew.getWriters());
            Set<String> samePerson = new HashSet<>(directorSet);
            samePerson.retainAll(writerSet);

            TitleCrewResponseDto titleCrewResponseDto = new TitleCrewResponseDto();
            samePerson.forEach(s -> {
                NameBasics nameBasics = nameBasicsRepository.findByNconst(s);
                if(nameBasics == null)
                    throw new IMDBException(BusinessExceptionCode.IMDB_NAME_BASE_NOT_FOUND);

                if (nameBasics.getDeathYear() == null) {
                    titleCrewResponseDto.setTconst(titleCrew.getTconst());
                    titleCrewResponseDto.getDirectors().add(nameBasics);
                    titleCrewResponseDto.getWriters().add(nameBasics);
                }
            });
            responseDtos.add(titleCrewResponseDto);
        });


        return responseDtos;
    }
    public List<TitleBySpecialActorsResponseDto> getTitleBySpecialActors(String actorId1, String actorId2) throws IMDBException{
        List<TitlePrincipals> actor1TitleList = titlePrincipalsRepository.findActorByNconst(actorId1);
        List<TitlePrincipals> actor2TitleList = titlePrincipalsRepository.findActorByNconst(actorId2);

        Set<String> actor1Tconst = actor1TitleList.stream()
                .map(at -> at.getTconst())
                .collect(Collectors.toSet());

        Set<String> actor2Tconst = actor2TitleList.stream()
                .map(at -> at.getTconst())
                .collect(Collectors.toSet());

        actor1Tconst.retainAll(actor2Tconst);

        return actor1Tconst.stream().map(s -> {
            TitleBySpecialActorsResponseDto titleBySpecialActorsResponseDto = titleBySpecialActorsResponseMapper.mapToDto(titleBasicRepository.findByTconst(s));
            if(titleBySpecialActorsResponseDto == null)
                throw new IMDBException(BusinessExceptionCode.IMDB_TITLE_BASE_NOT_FOUND);
            titleBySpecialActorsResponseDto.setActor1(nameBasicsRepository.findByNconst(actorId1).getPrimaryName());
            titleBySpecialActorsResponseDto.setActor2(nameBasicsRepository.findByNconst(actorId2).getPrimaryName());
            return titleBySpecialActorsResponseDto;
        }).collect(Collectors.toList());

    }


    public List<TitleByHighestVoteAndRatingResponseDto> getTitleByHighestVoteAndRating(String genre) throws IMDBException{

        Map<Integer, TitleByHighestVoteAndRatingResponseDto> highestTitleByYear = new HashMap<>();
        for (TitleBasics titleBasic : titleBasicRepository.getTitleBasics().values()) {
            if (titleBasic.getGenres().contains(genre)) {
                if(!highestTitleByYear.containsKey(titleBasic.getStartYear()))
                    highestTitleByYear.put(titleBasic.getStartYear(),
                            titleByHighestVoteAndRatingResponseMapper.mapToDto(titleBasic));
                else{
                    TitleByHighestVoteAndRatingResponseDto current = highestTitleByYear.get(titleBasic.getStartYear());
                    TitleRatings titleRatings = titleRatingsRepository.findByTconst(titleBasic.getTconst());
                    if (titleRatings.getAverageRating() > current.getAverageRating() || (titleRatings.getAverageRating() == current.getAverageRating()
                            && titleRatings.getNumVotes() > current.getNumVotes())) {
                        highestTitleByYear.put(titleBasic.getStartYear(), titleByHighestVoteAndRatingResponseMapper.mapToDto(titleBasic));
                    }
                }
            }
        }
        return highestTitleByYear.values().stream().toList();
    }
}
