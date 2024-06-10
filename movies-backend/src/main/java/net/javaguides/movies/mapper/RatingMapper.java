package net.javaguides.movies.mapper;

import net.javaguides.movies.dto.RatingDto;
import net.javaguides.movies.entity.Rating;

public class RatingMapper {
    public static RatingDto mapToRatingDto(Rating Rating){
        return new RatingDto(
                Rating.getId(),
                Rating.getUserId(),
                Rating.getMovieId(),
                Rating.getRating()
        );
    }

    public static Rating mapToRating(RatingDto RatingDto){
        return new Rating(
                RatingDto.getId(),
                RatingDto.getUserId(),
                RatingDto.getMovieId(),
                RatingDto.getRating()
        );
    }
}
