package net.javaguides.movies.controller;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.RatingDto;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.service.RatingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private RatingService ratingService;


    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<List<RatingDto>> getEmployeeById(@PathVariable("id") int userId) {
        List<RatingDto> ratingDtos = ratingService.getAllRatingsById(userId);
        return ResponseEntity.ok(ratingDtos);
    }


}
