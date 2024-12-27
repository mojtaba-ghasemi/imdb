package com.imdb.imdb.model.imdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TitleBasics {
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private Boolean isAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;
    private List<String> genres;

    public static TitleBasics getObject(String[] objectString){
        TitleBasics titleBasics = new TitleBasics();
        titleBasics.setTconst( (objectString[0].equals("\\N"))?null:objectString[0]);
        titleBasics.setTitleType( (objectString[1].equals("\\N"))?null:objectString[1]);
        titleBasics.setPrimaryTitle( (objectString[2].equals("\\N"))?null:objectString[2]);
        titleBasics.setOriginalTitle( (objectString[3].equals("\\N"))?null:objectString[3]);
        titleBasics.setIsAdult( (objectString[4].equals("\\N"))?null:Boolean.getBoolean(objectString[4]));
        titleBasics.setStartYear( (objectString[5].equals("\\N"))?null:Integer.parseInt(objectString[5]));
        titleBasics.setEndYear( (objectString[6].equals("\\N"))?null:Integer.parseInt(objectString[6]));
        titleBasics.setRuntimeMinutes( (objectString[7].equals("\\N"))?null:Integer.parseInt(objectString[7]));


        titleBasics.genres = (objectString[8].equals("\\N"))?null: Arrays.stream(objectString[8].split(",")).toList();


        return titleBasics;
    }

}
