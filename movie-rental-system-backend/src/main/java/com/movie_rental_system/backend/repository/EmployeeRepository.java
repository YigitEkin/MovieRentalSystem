package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
