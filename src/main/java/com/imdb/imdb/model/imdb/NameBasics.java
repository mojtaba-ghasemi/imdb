package com.imdb.imdb.model.imdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

@Data
//@Document
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NameBasics {

    private String nconst;
    private String primaryName;
    private String birthYear;
    private String deathYear;
    private List<String> primaryProfession;
    private List<String> knownForTitles;


    public static NameBasics getObject(String[] objectString){
        NameBasics nameBasics = new NameBasics();
        nameBasics.setNconst( (objectString[0].equals("\\N"))?null:objectString[0]);
        nameBasics.setPrimaryName( (objectString[1].equals("\\N"))?null:objectString[1]);
        nameBasics.setBirthYear( (objectString[2].equals("\\N"))?null:objectString[2]);
        nameBasics.setDeathYear( (objectString[3].equals("\\N"))?null:objectString[3]);

        nameBasics.primaryProfession = (objectString[4].equals("\\N"))?null: Arrays.stream(objectString[4].split(",")).toList();
        nameBasics.knownForTitles = (objectString[5].equals("\\N"))?null: Arrays.stream(objectString[5].split(",")).toList();
        return nameBasics;
    }

}
