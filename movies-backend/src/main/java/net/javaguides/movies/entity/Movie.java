package net.javaguides.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movies")
public class Movie {
    @Id // primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="movieId")
    private int movieId;
    @Column(name="Title")
    private String title;
    @Column(name="PosterPath")
    private String posterPath;
    @Column(name="Language")
    private String language;
    @Column(name="Overview")
    private String overview;
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name="Runtime")
    private Double runtime;
    @Column(name = "VoteAverage")
    private Double voteAverage;

}
