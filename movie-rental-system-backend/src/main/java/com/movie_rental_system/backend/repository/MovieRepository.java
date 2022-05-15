package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>{

    @Query(value = "SELECT * FROM movie", nativeQuery = true)
    List<Movie> getAll();

    @Query(value = "SELECT * FROM movie WHERE employee_name = ?1", nativeQuery = true)
    List<Movie> findByEmployeeName(String name);
}