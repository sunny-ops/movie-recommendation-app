package net.javaguides.movies.controller;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.RatingDto;
import net.javaguides.movies.dto.RecommendationDto;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.entity.Recommendation;
import net.javaguides.movies.service.RecommendationService;
import net.javaguides.movies.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    private RecommendationService recommendationService;


    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<RecommendationDto> getRecommendationById(@PathVariable("id") int recommendationId) {
        RecommendationDto recommendationDto = recommendationService.getRecommendationById(recommendationId);
        return ResponseEntity.ok(recommendationDto);
    }

    // Build Get Employee REST API
    @GetMapping("/movies/{id}")
    public ResponseEntity<List<MovieDto>> getAllRecommendedMoviesById(@PathVariable("id") int recommendationId) {
        List<MovieDto> movieDtos = recommendationService.getAllRecommendedMoviesById(recommendationId);
        return ResponseEntity.ok(movieDtos);
    }


}
