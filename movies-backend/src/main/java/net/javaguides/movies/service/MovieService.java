package net.javaguides.movies.service;

import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.UserDto;

public interface MovieService {


    MovieDto getMovieById(int movieId);

}
