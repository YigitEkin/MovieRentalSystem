package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM User", nativeQuery = true)
    List<User> getAll();
}
