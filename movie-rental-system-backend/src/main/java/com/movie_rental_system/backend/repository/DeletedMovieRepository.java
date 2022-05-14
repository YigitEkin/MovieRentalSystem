package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.DeletedMovie;
import com.movie_rental_system.backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeletedMovieRepository extends JpaRepository<DeletedMovie, Integer> {
    @Query(value = "SELECT * FROM deleted_movie", nativeQuery = true)
    List<Movie> getAll();
}
