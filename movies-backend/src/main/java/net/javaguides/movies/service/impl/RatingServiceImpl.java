package net.javaguides.movies.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.RatingDto;
import net.javaguides.movies.entity.Rating;
import net.javaguides.movies.mapper.RatingMapper;
import net.javaguides.movies.repository.RatingRepository;
import net.javaguides.movies.repository.UserRepository;
import net.javaguides.movies.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;

    @Override
    public List<RatingDto> getAllRatingsById(int userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        return ratings.stream()
                .map(rating -> RatingMapper.mapToRatingDto(rating))
                .collect(Collectors.toList());

    }
}
