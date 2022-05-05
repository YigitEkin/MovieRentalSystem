package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.MovieReviewDTO;
import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.MovieReview;
import com.movie_rental_system.backend.exception.InvalidDateFormatException;
import com.movie_rental_system.backend.exception.MovieReviewAlreadyExistsException;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.MovieRepository;
import com.movie_rental_system.backend.repository.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.DataFormatException;

@Service
public class MovieReviewService {
    private final MovieReviewRepository movieReviewRepository;
    private final MovieRepository movieRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public MovieReviewService(MovieReviewRepository movieReviewRepository, MovieRepository movieRepository, CustomerRepository customerRepository) {
        this.movieReviewRepository = movieReviewRepository;
        this.movieRepository = movieRepository;
        this.customerRepository = customerRepository;
    }

    // get all movie reviews
    public List<MovieReview> getAllMovieReviews() {
        return movieReviewRepository.findAll();
    }

    // get movie review by id
    public MovieReview getMovieReviewById(Long id) {
        MovieReview movieReview = movieReviewRepository.findById(id).orElse(null);
        if(movieReview == null)
            throw new RuntimeException("Movie review with id " + id + " not found");
        return movieReview;
    }

    // get movie reviews by movie id
    public List<MovieReview> getMovieReviewsByMovieId(int id) {
        if(movieRepository.findById(id).orElse(null) == null)
            throw new RuntimeException("Movie with id " + id + " not found");
        return movieReviewRepository.findByMovieId(id);
    }

    // get movie reviews by customer name
    public List<MovieReview> getMovieReviewsByCustomerName(String name) {
        Customer customer = customerRepository.findById(name).orElse(null);
        if (customer == null)
            throw new RuntimeException("Customer with name " + name + " not found");
        return movieReviewRepository.findByCustomerName(name);
    }

    // create movie review
    public MovieReview createMovieReview(MovieReviewDTO movieReview) {
        Customer customer = customerRepository.findById(movieReview.getCustomer_name()).orElse(null);
        Movie movie = movieRepository.findById(movieReview.getMovie_id()).orElse(null);
        System.out.println(movieReview.getRating());
        if (customer == null)
            throw new RuntimeException("Customer with name " + movieReview.getCustomer_name() + " not found");
        if (movie == null)
            throw new RuntimeException("Movie with id " + movieReview.getMovie_id() + " not found");
        try {
            return movieReviewRepository.save(
                    new MovieReview(0L, movie, customer, movieReview.getRating(), movieReview.getReview_message(),
                    movieReview.isSpoiler(), movieReview.getNet_like(), new SimpleDateFormat("yyyy-MM-dd").parse(movieReview.getReview_date())));
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + movieReview.getReview_date() + " should be yyyy-MM-dd");
        }
    }

    // update movie review
    public MovieReview updateMovieReview(Long id, MovieReviewDTO movieReview) {
        MovieReview movieReviewToUpdate = movieReviewRepository.findById(id).orElse(null);
        Customer customer = customerRepository.findById(movieReview.getCustomer_name()).orElse(null);
        Movie movie = movieRepository.findById(movieReview.getMovie_id()).orElse(null);
        if (movieReviewToUpdate == null)
            throw new RuntimeException("Movie review with id " + id + " not found");
        if (customer == null)
            throw new RuntimeException("Customer with name " + movieReview.getCustomer_name() + " not found");
        if (movie == null)
            throw new RuntimeException("Movie with id " + movieReview.getMovie_id() + " not found");

        try {
            return movieReviewRepository.save(
                    new MovieReview(0L, movie, customer, movieReview.getRating(), movieReview.getReview_message(),
                            movieReview.isSpoiler(), movieReview.getNet_like(), new SimpleDateFormat("yyyy-MM-dd").parse(movieReview.getReview_date())));
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + movieReview.getReview_date() + " should be yyyy-MM-dd");
        }
    }

    // delete movie review
    public MovieReview deleteMovieReview(Long id) {
        MovieReview movieReview = movieReviewRepository.findById(id).orElse(null);
        if (movieReview == null)
            throw new RuntimeException("Movie review with id " + id + " not found");
        movieReviewRepository.deleteById(id);
        return movieReview;
    }




}
