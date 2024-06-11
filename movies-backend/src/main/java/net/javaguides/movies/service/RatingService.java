package net.javaguides.movies.service;

import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.RatingDto;
import net.javaguides.movies.dto.UserDto;

import java.util.List;

public interface RatingService {

    List<RatingDto> getAllRatingsById(int userId);
    List<MovieDto> getAllRecommendedMoviesByUserId(int userId);


}
