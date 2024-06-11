package net.javaguides.movies.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.RecommendationDto;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.entity.Recommendation;
import net.javaguides.movies.entity.User;
import net.javaguides.movies.exception.ResourceNotFoundException;
import net.javaguides.movies.mapper.RecommendationMapper;
import net.javaguides.movies.mapper.UserMapper;
import net.javaguides.movies.repository.RecommendationRepository;
import net.javaguides.movies.repository.UserRepository;
import net.javaguides.movies.service.RecommendationService;
import net.javaguides.movies.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private RecommendationRepository recommendationRepository;


    @Override
    public RecommendationDto getRecommendationById(int recommendationId) {
        Recommendation recommendation = recommendationRepository.findById(recommendationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recommendation is not exists with given id : " + recommendationId));

        return RecommendationMapper.mapToRecommendationDto(recommendation);
    }
}
