package net.javaguides.movies.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private int movieId;
    private String title;
    private String posterPath;
    private String language;
    private String overview;
    private Date releaseDate;
    private Double runtime;
    private Double voteAverage;
}
