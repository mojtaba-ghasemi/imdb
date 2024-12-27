package com.imdb.imdb.model.imdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TitleEpisode {
    private String tconst;
    private String parentTconst;
    private Integer seasonNumber;
    private Integer episodeNumber;

    public static TitleEpisode getObject(String[] objectString){
        TitleEpisode titleEpisode = new TitleEpisode();
        titleEpisode.setTconst( (objectString[0].equals("\\N"))?null:objectString[0]);
        titleEpisode.setParentTconst( (objectString[1].equals("\\N"))?null:objectString[1]);
        titleEpisode.setSeasonNumber( (objectString[2].equals("\\N"))?null:Integer.parseInt(objectString[2]));
        titleEpisode.setEpisodeNumber( (objectString[3].equals("\\N"))?null:Integer.parseInt(objectString[3]));

        return titleEpisode;
    }

}
