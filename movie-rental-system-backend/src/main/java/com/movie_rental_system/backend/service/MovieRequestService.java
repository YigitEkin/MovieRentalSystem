package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Card;
import com.movie_rental_system.backend.entity.MovieRequest;
import com.movie_rental_system.backend.repository.CardRepository;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.MovieRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovieRequestService {
    private final MovieRequestRepository movieRequestRepository;

    @Autowired
    public MovieRequestService(MovieRequestRepository repo, CustomerRepository customerRepository) {
        this.movieRequestRepository = repo;
    }

    // get all
    public List<MovieRequest> getAll() {
        return movieRequestRepository.findAll();
    }

    // get by id
    public MovieRequest findRequestById(Integer id) {
        return movieRequestRepository.findById(id).orElse(null);
    }

    // get by customer id
    public List<MovieRequest> getRequestsOfCustomer(String customerName) {
        return movieRequestRepository.findRequestsOfCustomer(customerName);
    }

    // create
    public MovieRequest creatMovieRequest(MovieRequest movieRequest) {
        Objects.requireNonNull(movieRequest, "movie request cannot be null");
        return movieRequestRepository.save(movieRequest);
    }

    // delete
    public MovieRequest deleteMovieRequest(Integer id) {
        MovieRequest temp = movieRequestRepository.findById(id).orElse(null);
        movieRequestRepository.deleteById(id);
        return temp;
    }
}
