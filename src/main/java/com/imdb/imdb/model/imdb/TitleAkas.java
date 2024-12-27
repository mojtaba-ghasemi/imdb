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
public class TitleAkas {
    private String titleId;
    private Integer ordering;
    private String title;
    private String region;
    private String language;
    private List<TitleAkasType> types;
    private List<String> attributes;
    private Boolean isOriginalTitle;

    public static TitleAkas getObject(String[] objectString){
        TitleAkas titleAkas = new TitleAkas();
        titleAkas.setTitleId( (objectString[0].equals("\\N"))?null:objectString[0]);
        titleAkas.setOrdering( (objectString[1].equals("\\N"))?null:Integer.parseInt(objectString[1]));
        titleAkas.setTitle( (objectString[2].equals("\\N"))?null:objectString[2]);
        titleAkas.setRegion( (objectString[3].equals("\\N"))?null:objectString[3]);
        titleAkas.setLanguage( (objectString[4].equals("\\N"))?null:objectString[4]);



        titleAkas.types = (objectString[5].equals("\\N"))?null:
                Arrays.stream(objectString[5].split(",")).map(s -> TitleAkasType.valueOf(s)).toList();
        titleAkas.attributes = (objectString[6].equals("\\N"))?null: Arrays.stream(objectString[6].split(",")).toList();

        titleAkas.setIsOriginalTitle( (objectString[7].equals("\\N"))?null:Boolean.getBoolean(objectString[7]));

        return titleAkas;
    }

}
