package net.javaguides.movies.repository;

import net.javaguides.movies.entity.Recommendation;
import net.javaguides.movies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}
