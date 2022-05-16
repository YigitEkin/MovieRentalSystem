package com.movie_rental_system.backend.repository;
import com.movie_rental_system.backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT * FROM movie WHERE employee_name = ?1", nativeQuery = true)
    List<Movie> findByEmployeeName(String name);

    // get all movies by genre
    @Query(value = "SELECT * FROM movie WHERE genre = ?1", nativeQuery = true)
    Set<Movie> findByGenre(String genre);

    // get all movies by exact price
    @Query(value = "SELECT * FROM movie WHERE price = ?1", nativeQuery = true)
    Set<Movie> findByPrice(double price);

    // get all movies by price range
    @Query(value = "SELECT * FROM movie WHERE price BETWEEN ?1 AND ?2", nativeQuery = true)
    Set<Movie> findByPriceRange(double min, double max);

    // get all movies by rating
    @Query(value = "SELECT * FROM movie WHERE rating = ?1", nativeQuery = true)
    Set<Movie> findByRating(double rating);

    // get all movies by rating range
    @Query(value = "SELECT * FROM movie WHERE rating BETWEEN ?1 AND ?2", nativeQuery = true)
    Set<Movie> findByRatingRange(double min, double max);

    // get all movies by year
    @Query(value = "SELECT * FROM movie WHERE production_year = ?1", nativeQuery = true)
    Set<Movie> findByYear(int year);

    // get all movies by year range
    @Query(value = "SELECT * FROM movie WHERE production_year BETWEEN ?1 AND ?2", nativeQuery = true)
    Set<Movie> findByYearRange(int min, int max);

    // get all movies by director
    @Query(value = "SELECT * FROM movie WHERE director = ?1", nativeQuery = true)
    Set<Movie> findByDirector(String director);

    // get all movies by exact title
    @Query(value = "SELECT * FROM movie WHERE movie_title = ?1", nativeQuery = true)
    Set<Movie> findByTitle(String title);

    // get all movies whose title contains a string
    @Query("FROM Movie m WHERE m.movie_title LIKE %:title%")
    Set<Movie> findByTitleContains(@Param("title") String title);
}