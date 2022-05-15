package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

    // Query to get the average rating of a movie
    @Query(value = "SELECT AVG(rating) FROM movie_review WHERE movie_id = ?1", nativeQuery = true)
    Double getAverageRating(Integer movieId);

    // Query to get movie reviews by movie id
    @Query(value = "SELECT * FROM movie_review WHERE movie_id = ?1", nativeQuery = true)
    List<MovieReview> findByMovieId(int movieId);

    // Query to get movie reviews by user id
    @Query(value = "SELECT * FROM movie_review WHERE customer_name = ?1", nativeQuery = true)
    List<MovieReview> findByCustomerName(String userName);
}
