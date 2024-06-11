package net.javaguides.movies.mapper;

import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.entity.Movie;
import net.javaguides.movies.entity.User;

public class MovieMapper {
    public static MovieDto mapToMovieDto(Movie movie){
        return new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getPosterPath(),
                movie.getLanguage(),
                movie.getOverview(),
                movie.getReleaseDate(),
                movie.getRuntime(),
                movie.getVoteAverage()
        );
    }

    public static Movie mapToMovie(MovieDto movieDto){
        return new Movie(
                movieDto.getMovieId(),
                movieDto.getTitle(),
                movieDto.getPosterPath(),
                movieDto.getLanguage(),
                movieDto.getOverview(),
                movieDto.getReleaseDate(),
                movieDto.getRuntime(),
                movieDto.getVoteAverage()
        );
    }
}
