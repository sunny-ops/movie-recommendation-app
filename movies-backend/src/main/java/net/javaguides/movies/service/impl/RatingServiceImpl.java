package net.javaguides.movies.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.RatingDto;
import net.javaguides.movies.entity.Movie;
import net.javaguides.movies.entity.Rating;
import net.javaguides.movies.entity.Recommendation;
import net.javaguides.movies.exception.ResourceNotFoundException;
import net.javaguides.movies.mapper.MovieMapper;
import net.javaguides.movies.mapper.RatingMapper;
import net.javaguides.movies.repository.MovieRepository;
import net.javaguides.movies.repository.RatingRepository;
import net.javaguides.movies.repository.RecommendationRepository;
import net.javaguides.movies.repository.UserRepository;
import net.javaguides.movies.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static net.javaguides.movies.service.impl.RecommendationServiceImpl.extractIntegers;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;
    private RecommendationRepository recommendationRepository;
    private MovieRepository movieRepository;

    @Override
    public List<RatingDto> getAllRatingsById(int userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        return ratings.stream()
                .map(rating -> RatingMapper.mapToRatingDto(rating))
                .collect(Collectors.toList());

    }

    @Override
    public List<MovieDto> getAllRecommendedMoviesByUserId(int userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        // 计算前 10% 的数量
        int top10PercentCount = (int) Math.ceil(ratings.size() * 0.1);

        // 获取前 10% 的 movieId
        List<Integer> top10PercentMovieIds = ratings.stream()
                .sorted(Comparator.comparingDouble(Rating::getRating).reversed())
                .limit(top10PercentCount)
                .map(Rating::getMovieId)
                .collect(Collectors.toList());


        List<MovieDto> movieDtos = new ArrayList<>();
        for(int movieId: top10PercentMovieIds) {
            Recommendation recommendation = recommendationRepository.findById(movieId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Recommendation is not exists with given id : " + movieId));
            String recommends = recommendation.getRecommends();
            List<Integer> integers = extractIntegers(recommends);

//            System.out.println(integers);
            for(int value: integers) {
                Movie movie = movieRepository.findById(value)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("User is not exists with given id : " + value));
                movieDtos.add(MovieMapper.mapToMovieDto(movie));
            }


        }
        List<MovieDto> topTenMovies = movieDtos.stream()
                .sorted(Comparator.comparingDouble(MovieDto::getVoteAverage).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return topTenMovies;
    }


}
