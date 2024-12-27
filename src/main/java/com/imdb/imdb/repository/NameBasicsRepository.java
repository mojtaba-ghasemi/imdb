package com.imdb.imdb.repository;

import com.imdb.imdb.model.imdb.NameBasics;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service
public class NameBasicsRepository {
    private Map<String, NameBasics> nameBasics = new HashMap<>();

    public NameBasics findByNconst(String nconst){
        return nameBasics.get(nconst);
    }
}
