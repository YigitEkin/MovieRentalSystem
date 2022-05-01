package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.NewCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewCustomerRepository extends JpaRepository<NewCustomer, String> {

}
