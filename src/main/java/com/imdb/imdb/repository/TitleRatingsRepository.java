package com.imdb.imdb.repository;

import com.imdb.imdb.model.imdb.TitleBasics;
import com.imdb.imdb.model.imdb.TitleRatings;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class TitleRatingsRepository {
    private Map<String, TitleRatings> titleRatings = new HashMap<>();
    public TitleRatings findByTconst(String tconst){
        return titleRatings.get(tconst);
    }
}
