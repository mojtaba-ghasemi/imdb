package com.imdb.imdb.repository;

import com.imdb.imdb.exception.IMDBException;
import com.imdb.imdb.model.imdb.NameBasics;
import com.imdb.imdb.model.imdb.TitleBasics;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class TitleBasicRepository {
    private Map<String, TitleBasics> titleBasics = new HashMap<>();
    public TitleBasics findByTconst(String tconst){
        return titleBasics.get(tconst);
    }
}
