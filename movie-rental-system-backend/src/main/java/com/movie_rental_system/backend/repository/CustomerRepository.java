package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CustomerRepository extends JpaRepository<Customer, String> {

    // remove favorite movie from customer's favorite list
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customer_favorite_movie WHERE customer_id = ?1 AND movie_id = ?2", nativeQuery = true)
    void deleteFavoriteMovie(String customerId, Integer movieId);
}
