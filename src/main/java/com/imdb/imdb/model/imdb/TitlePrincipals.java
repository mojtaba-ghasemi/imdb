package com.imdb.imdb.model.imdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TitlePrincipals {
    private String tconst;
    private Integer ordering;
    private String nconst;
    private String category;
    private String job;
    private String characters;

    public static TitlePrincipals getObject(String[] objectString){

        TitlePrincipals titlePrincipals = new TitlePrincipals();
        titlePrincipals.setTconst( (objectString[0].equals("\\N"))?null:objectString[0]);
        titlePrincipals.setOrdering( (objectString[1].equals("\\N"))?null:Integer.parseInt(objectString[1]));
        titlePrincipals.setNconst( (objectString[2].equals("\\N"))?null:objectString[2]);
        titlePrincipals.setCategory( (objectString[3].equals("\\N"))?null:objectString[3]);
        titlePrincipals.setJob( (objectString[4].equals("\\N"))?null:objectString[4]);
        titlePrincipals.setCharacters( (objectString[5].equals("\\N"))?null:objectString[5]);

        return titlePrincipals;
    }

}
