package com.imdb.imdb.rest;

import com.imdb.imdb.dto.TitleByHighestVoteAndRatingResponseDto;
import com.imdb.imdb.dto.TitleBySpecialActorsResponseDto;
import com.imdb.imdb.dto.TitleCrewResponseDto;
import com.imdb.imdb.exception.IMDBException;
import com.imdb.imdb.filter.RequestCountingFilter;
import com.imdb.imdb.service.ImdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imdb")
@RequiredArgsConstructor
@Slf4j
public class ImdbController {

    private final ImdbService imdbService;
    private final RequestCountingFilter requestCountingFilter;

    @GetMapping("/get-title-by-directors-same-writers")
    public ResponseEntity<List<TitleCrewResponseDto>> getTitleByDirectorsSameWriters() throws IMDBException {
        return ResponseEntity.ok(imdbService.getTitleByDirectorsSameWriters());
    }

    @GetMapping("/get-title-by-special-actors")
    public ResponseEntity<List<TitleBySpecialActorsResponseDto>> getTitleBySpecialActors(
            @RequestParam String actorId1, @RequestParam String actorId2
    ) throws IMDBException {
        return ResponseEntity.ok(imdbService.getTitleBySpecialActors(actorId1, actorId2));
    }

    @GetMapping("/get-title-by-highest-vote-rating")
    public ResponseEntity<List<TitleByHighestVoteAndRatingResponseDto>> getTitleBySpecialActors(
            @RequestParam String genre
    ) throws IMDBException {
        return ResponseEntity.ok(imdbService.getTitleByHighestVoteAndRating(genre));
    }

    @GetMapping("/request-count")
    public String getRequestCount() {
        int count = requestCountingFilter.getRequestCount();
        return "Total HTTP requests since startup: " + count;
    }
}
