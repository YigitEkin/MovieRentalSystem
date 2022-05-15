package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Integer> {

}
