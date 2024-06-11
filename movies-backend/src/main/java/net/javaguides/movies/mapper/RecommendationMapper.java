package net.javaguides.movies.mapper;

import net.javaguides.movies.dto.RecommendationDto;
import net.javaguides.movies.entity.Recommendation;
import net.javaguides.movies.entity.Recommendation;

public class RecommendationMapper {
    public static RecommendationDto mapToRecommendationDto(Recommendation recommendation){
        return new RecommendationDto(
                recommendation.getId(),
                recommendation.getTitle(),
                recommendation.getRecommends()
        );
    }

    public static Recommendation mapToRating(RecommendationDto recommendationDto){
        return new Recommendation(
                recommendationDto.getId(),
                recommendationDto.getTitle(),
                recommendationDto.getRecommends()
        );
    }
}
