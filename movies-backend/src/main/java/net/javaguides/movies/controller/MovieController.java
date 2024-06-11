package net.javaguides.movies.controller;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.MovieDto;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.service.MovieService;
import net.javaguides.movies.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private MovieService movieService;


    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") int movieId) {
        MovieDto movieDto = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movieDto);
    }


}
