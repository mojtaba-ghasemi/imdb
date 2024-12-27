package com.imdb.imdb.repository;


import com.imdb.imdb.model.imdb.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


@Configuration
public class DataLoader {

    @Value("${imdb.dataset.title.crew.csv}")
    private String imdbDatasetTitleCrewCsv;

    @Value("${imdb.dataset.name.basics.csv}")
    private String imdbDatasetNameBasicsCsv;

    @Value("${imdb.dataset.title.principals.csv}")
    private String imdbDatasetTitlePrincipalsCsv;

    @Value("${imdb.dataset.title.episode.csv}")
    private String imdbDatasetTitleEpisodeCsv;

    @Value("${imdb.dataset.title.ratings.csv}")
    private String imdbDatasetTitleRatingsCsv;

    @Value("${imdb.dataset.title.basics.csv}")
    private String imdbDatasetTitleBasicsCsv;

    @Value("${imdb.dataset.title.akas.csv}")
    private String imdbDatasetTitleAkasCsv;

    @Autowired
    private TitleCrewRepository titleCrewRepository;
    @Autowired
    private NameBasicsRepository nameBasicsRepository;
    @Autowired
    private TitlePrincipalsRepository titlePrincipalsRepository;
    @Autowired
    private TitleBasicRepository titleBasicRepository;
    @Autowired
    private TitleRatingsRepository titleRatingsRepository;

    @Bean
    public String startDataLoader() throws IOException, URISyntaxException, InterruptedException {

        CompletableFuture<String> task1 = titleCrew_loadCsv_task();
        CompletableFuture<String> task2 = nameBasics_loadCsv_task();
        CompletableFuture<String> task3 = titlePrincipals_loadCsv_task();
        CompletableFuture<String> task4 = titleEpisodes_loadCsv_task();
        CompletableFuture<String> task5 = titleRatings_loadCsv_task();
        CompletableFuture<String> task6 = titleBasics_loadCsv_task();
        CompletableFuture<String> task7 = titleAkas_loadCsv_task();

        CompletableFuture.allOf(task1, task2, task3, task4, task5, task6, task7).join();
        return "";
    }

    @Async
    public CompletableFuture<String> titleCrew_loadCsv_task() throws IOException, URISyntaxException {
        loadCsv(imdbDatasetTitleCrewCsv, (Object objectString) -> {
            TitleCrew titleCrew = TitleCrew.getObject((String[]) objectString);
            titleCrewRepository.getTitleCrews().add(titleCrew);
            return null;
        });
        return CompletableFuture.completedFuture("");
    }

    @Async
    public CompletableFuture<String> nameBasics_loadCsv_task() throws IOException, URISyntaxException {
        loadCsv(imdbDatasetNameBasicsCsv, (Object objectString) -> {
            NameBasics nameBasics = NameBasics.getObject((String[]) objectString);
            nameBasicsRepository.getNameBasics().put(nameBasics.getNconst(), nameBasics);
            return null;
        });
        return CompletableFuture.completedFuture("");
    }

    @Async
    public CompletableFuture<String> titlePrincipals_loadCsv_task() throws IOException, URISyntaxException {
        loadCsv(imdbDatasetTitlePrincipalsCsv, (Object objectString) -> {
            TitlePrincipals titlePrincipals = TitlePrincipals.getObject((String[]) objectString);
            titlePrincipalsRepository.getTitlePrincipals().add(titlePrincipals);
            return null;
        });
        return CompletableFuture.completedFuture("");
    }

    @Async
    public CompletableFuture<String> titleEpisodes_loadCsv_task() throws IOException, URISyntaxException {
        loadCsv(imdbDatasetTitleEpisodeCsv, (Object objectString) -> {
            TitleEpisode titleEpisode = TitleEpisode.getObject((String[]) objectString);
            titleCrewRepository.getTitleEpisodes().add(titleEpisode);
            return null;
        });
        return CompletableFuture.completedFuture("");
    }

    @Async
    public CompletableFuture<String> titleRatings_loadCsv_task() throws IOException, URISyntaxException {
        loadCsv(imdbDatasetTitleRatingsCsv, (Object objectString) -> {
            TitleRatings titleRatings = TitleRatings.getObject((String[]) objectString);
            titleRatingsRepository.getTitleRatings().put(titleRatings.getTconst(), titleRatings);
            return null;
        });
        return CompletableFuture.completedFuture("");
    }

    @Async
    public CompletableFuture<String> titleBasics_loadCsv_task() throws IOException, URISyntaxException {
        loadCsv(imdbDatasetTitleBasicsCsv, (Object objectString) -> {
            TitleBasics titleBasics = TitleBasics.getObject((String[]) objectString);
            titleBasicRepository.getTitleBasics().put(titleBasics.getTconst(), titleBasics);
            return null;
        });
        return CompletableFuture.completedFuture("");
    }

    @Async
    public CompletableFuture<String> titleAkas_loadCsv_task() throws IOException, URISyntaxException {
        loadCsv(imdbDatasetTitleAkasCsv, (Object objectString) -> {
            TitleAkas titleAkas = TitleAkas.getObject((String[]) objectString);
            titleCrewRepository.getTitleAkas().add(titleAkas);
            return null;
        });
        return CompletableFuture.completedFuture("");
    }

    private void loadCsv(String csvFileName, Function function) throws IOException, URISyntaxException {

        URL res = getClass().getClassLoader().getResource("dataSource/" + csvFileName);
        File filePath = Paths.get(res.toURI()).toFile();

        CSVFormat format = CSVFormat.DEFAULT.withDelimiter('\t');
        int rowNum = 0;
        try (CSVParser parser = CSVParser.parse(new FileReader(filePath), format)) {
            for (CSVRecord record : parser) {
                String[] recordArray = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    recordArray[i] = record.get(i);
                }

                if (rowNum > 0) {
                    function.apply(recordArray);
                }
                rowNum++;
            }
        }
    }
}
