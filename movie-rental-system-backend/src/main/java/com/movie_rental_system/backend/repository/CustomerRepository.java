package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, String> {

}
