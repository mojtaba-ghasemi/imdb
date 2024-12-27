package com.imdb.imdb.repository;

import com.imdb.imdb.model.imdb.TitlePrincipals;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class TitlePrincipalsRepository {
    private List<TitlePrincipals> titlePrincipals = new ArrayList<>();

    public List<TitlePrincipals> findActorByNconst(String nconst){
        return titlePrincipals.stream().filter(t -> t.getNconst().equals(nconst))
                .filter(t -> t.getCategory().contains("actor")).toList();
    }
}
