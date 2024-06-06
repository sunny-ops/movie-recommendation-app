package net.javaguides.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ratings")
public class Rating {
    @Id // primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="userId")
    private int userId;
    @Column(name="movieId")
    private int movieId;
    @Column(name="rating")
    private double rating;
}
