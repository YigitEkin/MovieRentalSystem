package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.MovieRequestDTO;
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
    private final CustomerRepository customerRepository;

    @Autowired
    public MovieRequestService(MovieRequestRepository repo, CustomerRepository customerRepository) {
        this.movieRequestRepository = repo;
        this.customerRepository =  customerRepository;
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
    public MovieRequest creatMovieRequest(MovieRequestDTO movieRequest) {
        Objects.requireNonNull(movieRequest, "movie request cannot be null");
        return movieRequestRepository.save(new MovieRequest(movieRequest.getRequest_id(), movieRequest.getMovie_title(), movieRequest.getProduction_year(),
                movieRequest.getDescription(), customerRepository.findById(movieRequest.getCustomer_name()).get()));
    }

    // delete
    public MovieRequest deleteMovieRequest(Integer id) {
        MovieRequest temp = movieRequestRepository.findById(id).orElse(null);
        movieRequestRepository.deleteById(id);
        return temp;
    }
}
