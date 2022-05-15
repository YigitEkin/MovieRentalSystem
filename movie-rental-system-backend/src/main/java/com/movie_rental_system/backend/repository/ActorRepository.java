package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

        @Query(value = "SELECT * FROM actor", nativeQuery = true)
        List<Actor> getAll();
}

