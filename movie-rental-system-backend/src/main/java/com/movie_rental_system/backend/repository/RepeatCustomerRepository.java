package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.RepeatCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepeatCustomerRepository extends JpaRepository<RepeatCustomer, String> {


}
