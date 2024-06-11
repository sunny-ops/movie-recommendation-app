package net.javaguides.movies.repository;

import net.javaguides.movies.entity.Movie;
import net.javaguides.movies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
