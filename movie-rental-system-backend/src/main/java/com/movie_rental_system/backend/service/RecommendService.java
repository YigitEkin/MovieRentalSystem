package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.RecommendDTO;
import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.Recommend;
import com.movie_rental_system.backend.exception.CustomerNotFoundException;
import com.movie_rental_system.backend.exception.MovieNotFoundException;
import com.movie_rental_system.backend.exception.RecommendNotFoundException;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.MovieRepository;
import com.movie_rental_system.backend.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {
    private final MovieRepository movieRepository;
    private final RecommendRepository recommendRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RecommendService(MovieRepository movieRepository, RecommendRepository recommendRepository, CustomerRepository customerRepository) {
        this.movieRepository = movieRepository;
        this.recommendRepository = recommendRepository;
        this.customerRepository = customerRepository;
    }

    // get all recommendations
    public List<Recommend> getAllRecommendations() {
        return recommendRepository.findAll();
    }

    // get recommendation by id
    public Recommend getRecommendationById(Integer id) {
        Recommend recommend = recommendRepository.findById(id).orElse(null);
        if (recommend == null)
            throw new RecommendNotFoundException("Recommendation with id " + id + " not found");
        return recommend;
    }

    // get recommend by customer who is recommender
    public List<Recommend> getRecommendByRecommender(String customer_name) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        return recommendRepository.getRecommendByRecommenderUserName(customer_name);
    }

    // get recommend by customer who is recommender
    public List<Recommend> getRecommendByRecommended(String customer_name) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        return recommendRepository.getRecommendByRecommendedUserName(customer_name);
    }

    // create recommendation
    public Recommend createRecommendation(RecommendDTO recommendDTO) {
        Customer recommender = customerRepository.findById(recommendDTO.getRecommender_user_name()).orElse(null);
        Customer recommended = customerRepository.findById(recommendDTO.getRecommended_user_name()).orElse(null);
        Movie movie = movieRepository.findById(recommendDTO.getMovie_id()).orElse(null);
        if (recommender == null)
            throw new CustomerNotFoundException("Customer with name " + recommendDTO.getRecommender_user_name() + " not found");
        if (recommended == null)
            throw new CustomerNotFoundException("Customer with name " + recommendDTO.getRecommended_user_name() + " not found");
        if (movie == null)
            throw new MovieNotFoundException("Movie with id " + recommendDTO.getMovie_id() + " not found");
        Recommend recommend = new Recommend(0, recommended, recommender, movie, recommendDTO.getMessage());
        return recommendRepository.save(recommend);
    }

    // update recommendation
    public Recommend updateRecommendation(Integer id, RecommendDTO recommendDTO) {
        Recommend recommend = recommendRepository.findById(id).orElse(null);
        if (recommend == null)
            throw new RecommendNotFoundException("Recommendation with id " + id + " not found");
        Customer recommender = customerRepository.findById(recommendDTO.getRecommender_user_name()).orElse(null);
        Customer recommended = customerRepository.findById(recommendDTO.getRecommended_user_name()).orElse(null);
        Movie movie = movieRepository.findById(recommendDTO.getMovie_id()).orElse(null);
        if (recommender == null)
            throw new CustomerNotFoundException("Customer with name " + recommendDTO.getRecommender_user_name() + " not found");
        if (recommended == null)
            throw new CustomerNotFoundException("Customer with name " + recommendDTO.getRecommended_user_name() + " not found");
        if (movie == null)
            throw new MovieNotFoundException("Movie with id " + recommendDTO.getMovie_id() + " not found");
        recommend.setRecommender_user_name(recommender);
        recommend.setRecommended_user_name(recommended);
        recommend.setMovie_id(movie);
        recommend.setMessage(recommendDTO.getMessage());
        return recommendRepository.save(recommend);
    }

    // delete recommendation
    public Recommend deleteRecommendation(Integer id) {
        Recommend recommend = recommendRepository.findById(id).orElse(null);
        if (recommend == null)
            throw new RecommendNotFoundException("Recommendation with id " + id + " not found");
        recommendRepository.delete(recommend);
        return recommend;
    }
}
