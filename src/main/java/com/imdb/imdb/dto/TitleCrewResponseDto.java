package com.imdb.imdb.dto;

import com.imdb.imdb.model.imdb.NameBasics;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class TitleCrewResponseDto {
    public TitleCrewResponseDto(){
        directors = new ArrayList<>();
        writers = new ArrayList<>();
    }
    private String tconst;
    private List<NameBasics> directors;
    private List<NameBasics> writers;
}
