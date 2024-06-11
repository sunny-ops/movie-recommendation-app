package net.javaguides.movies.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.entity.Movie;
import net.javaguides.movies.entity.User;
import net.javaguides.movies.exception.ResourceNotFoundException;
import net.javaguides.movies.mapper.MovieMapper;
import net.javaguides.movies.mapper.UserMapper;
import net.javaguides.movies.repository.MovieRepository;
import net.javaguides.movies.repository.UserRepository;
import net.javaguides.movies.service.MovieService;
import net.javaguides.movies.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;


    @Override
    public MovieDto getMovieById(int movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User is not exists with given id : " + movieId));

        return MovieMapper.mapToMovieDto(movie);
    }
}
