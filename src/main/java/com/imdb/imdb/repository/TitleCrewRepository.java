package com.imdb.imdb.repository;

import com.imdb.imdb.dto.TitleCrewResponseDto;
import com.imdb.imdb.model.imdb.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
public class TitleCrewRepository {

    private List<TitleCrew> titleCrews = new ArrayList<>();

    public List<TitleCrew> findByDirectorsSameWriters(){
        List<TitleCrew> result = new ArrayList<>();
        titleCrews.forEach((titleCrew) -> {
            if(titleCrew.getDirectors() != null && titleCrew.getWriters() != null) {
                Set<String> directorSet = new HashSet<>(titleCrew.getDirectors());
                boolean hasSamePerson = titleCrew.getWriters().stream().anyMatch(directorSet::contains);
                if (hasSamePerson) {
                    result.add(titleCrew);
                }
            }
        });
        return result;
    }


//    private List<TitleEpisode> titleEpisodes = new ArrayList<>();
//
//
//    private List<TitleAkas> titleAkas = new ArrayList<>();
}
