package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Card;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.MovieRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRequestRepository extends JpaRepository<MovieRequest, Integer>{

    @Query(value = "SELECT * FROM movie_request", nativeQuery = true)
    List<MovieRequest> getAll();

    @Query("SELECT m FROM MovieRequest m where m.customer.user_name = ?1")
    List<MovieRequest> findRequestsOfCustomer(String customerName);
}