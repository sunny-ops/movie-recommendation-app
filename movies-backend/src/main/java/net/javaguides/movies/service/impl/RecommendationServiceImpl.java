package net.javaguides.movies.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.RecommendationDto;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.entity.Movie;
import net.javaguides.movies.entity.Rating;
import net.javaguides.movies.entity.Recommendation;
import net.javaguides.movies.entity.User;
import net.javaguides.movies.exception.ResourceNotFoundException;
import net.javaguides.movies.mapper.MovieMapper;
import net.javaguides.movies.mapper.RatingMapper;
import net.javaguides.movies.mapper.RecommendationMapper;
import net.javaguides.movies.mapper.UserMapper;
import net.javaguides.movies.repository.MovieRepository;
import net.javaguides.movies.repository.RecommendationRepository;
import net.javaguides.movies.repository.UserRepository;
import net.javaguides.movies.service.RecommendationService;
import net.javaguides.movies.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private RecommendationRepository recommendationRepository;
    private MovieRepository movieRepository;


    @Override
    public RecommendationDto getRecommendationById(int recommendationId) {
        Recommendation recommendation = recommendationRepository.findById(recommendationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recommendation is not exists with given id : " + recommendationId));
        System.out.println(recommendation.getRecommends());
        return RecommendationMapper.mapToRecommendationDto(recommendation);
    }

    @Override
    public List<MovieDto> getAllRecommendedMoviesById(int recommendationId) {
        Recommendation recommendation = recommendationRepository.findById(recommendationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recommendation is not exists with given id : " + recommendationId));
        String recommends = recommendation.getRecommends();
        List<Integer> integers = extractIntegers(recommends);
        List<MovieDto> movieDtos = new ArrayList<>();

        System.out.println(integers);
        for(int value: integers) {
            Movie movie = movieRepository.findById(value)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User is not exists with given id : " + value));
            movieDtos.add(MovieMapper.mapToMovieDto(movie));
        }

        return movieDtos;
    }

    public static List<Integer> extractIntegers(String input) {
        List<Integer> integers = new ArrayList<>();

        // 使用 Scanner 按行读取输入
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // 假设电影 ID 是行开始的第一个连续的整数，后面紧跟空格和电影名称
            String[] parts = line.trim().split("\\s+");
            if (parts.length > 0) {
                try {
                    // 解析第一个部分为整数
                    int movieId = Integer.parseInt(parts[0]);
                    integers.add(movieId);
                } catch (NumberFormatException e) {
                    // 忽略解析错误
                }
            }
        }
        scanner.close();

        return integers;

    }



}
