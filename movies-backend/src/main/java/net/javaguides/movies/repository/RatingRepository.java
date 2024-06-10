package net.javaguides.movies.repository;

import net.javaguides.movies.entity.Rating;
import net.javaguides.movies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByUserId(int userId);
}
