package com.imdb.imdb.model.imdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TitleCrew {

    private String tconst;

    private List<String> directors;
    private List<String> writers;

    public static TitleCrew getObject(String[] objectString){
        TitleCrew titleCrew = new TitleCrew();
        titleCrew.setTconst( (objectString[0].equals("\\N"))?null:objectString[0]);
        titleCrew.directors = (objectString[1].equals("\\N"))?null:  Arrays.stream(objectString[1].split(",")).toList() ;
        titleCrew.writers = (objectString[2].equals("\\N"))?null: Arrays.stream(objectString[2].split(",")).toList();
        return titleCrew;
    }
}
