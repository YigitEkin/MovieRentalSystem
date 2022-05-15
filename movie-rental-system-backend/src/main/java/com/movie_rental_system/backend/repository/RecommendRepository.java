package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Integer> {

    // get all recommends by recommender user name
    @Query(value = "SELECT * FROM recommend WHERE recommender_user_name = ?1", nativeQuery = true)
    List<Recommend> getRecommendByRecommenderUserName(String recommenderUserName);

    // get all recommends by recommended user name
    @Query(value = "SELECT * FROM recommend WHERE recommended_user_name = ?1", nativeQuery = true)
    List<Recommend> getRecommendByRecommendedUserName(String recommendedUserName);
}
