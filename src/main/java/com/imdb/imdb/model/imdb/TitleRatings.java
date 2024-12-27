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
public class TitleRatings {
    private String tconst;
    private Float averageRating;
    private Integer numVotes;

    public static TitleRatings getObject(String[] objectString) {
        TitleRatings titleRatings = new TitleRatings();
        titleRatings.setTconst((objectString[0].equals("\\N")) ? null : objectString[0]);
        titleRatings.setAverageRating((objectString[1].equals("\\N")) ? null : Float.parseFloat(objectString[1]));
        titleRatings.setNumVotes((objectString[2].equals("\\N")) ? null : Integer.parseInt(objectString[2]));
        return titleRatings;
    }
}
