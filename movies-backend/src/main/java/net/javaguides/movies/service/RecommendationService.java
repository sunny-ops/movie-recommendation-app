package net.javaguides.movies.service;

import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.RatingDto;
import net.javaguides.movies.dto.RecommendationDto;
import net.javaguides.movies.dto.UserDto;

import java.util.List;

public interface RecommendationService {

    RecommendationDto getRecommendationById(int recommendationId);
    List<MovieDto> getAllRecommendedMoviesById(int recommendationId);


}
