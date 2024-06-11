package net.javaguides.movies.service;

import net.javaguides.movies.dto.RecommendationDto;
import net.javaguides.movies.dto.UserDto;

public interface RecommendationService {

    RecommendationDto getRecommendationById(int recommendationId);

}
