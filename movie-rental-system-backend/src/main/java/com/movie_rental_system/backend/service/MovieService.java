package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.MovieDTO;
import com.movie_rental_system.backend.entity.*;
import com.movie_rental_system.backend.exception.CustomerNotFoundException;
import com.movie_rental_system.backend.exception.InvalidDateFormatException;
import com.movie_rental_system.backend.exception.MovieNotFoundException;
import com.movie_rental_system.backend.repository.DeletedMovieRepository;
import com.movie_rental_system.backend.repository.MovieRepository;
import com.movie_rental_system.backend.repository.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final DeletedMovieRepository deletedMovieRepository;
    private final EmployeeService employeeService;
    private final MovieReviewRepository movieReviewRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, EmployeeService employeeService, MovieReviewRepository movieReviewRepository,
                        DeletedMovieRepository deletedMovieRepository) {
        this.movieRepository = movieRepository;
        this.employeeService = employeeService;
        this.movieReviewRepository = movieReviewRepository;
        this.deletedMovieRepository = deletedMovieRepository;
    }


    public List<Movie> getAll(){
        // set the average rating for each movie
        List<Movie> movies = movieRepository.findAll();
        for(Movie movie : movies){
            Double averageRating = movieReviewRepository.getAverageRating(movie.getMovie_id());
            if(averageRating != null)
                movie.setAvg_rating(averageRating);
        }
        return movies;
    }

    public Movie findByMovieId(Integer id){
        Double averageRating = movieReviewRepository.getAverageRating(id);
        Movie movie = movieRepository.findById(id).orElse(null);
        if(averageRating != null && movie != null)
            movie.setAvg_rating(averageRating);
        return movie;
    }

    public Movie addNewMovie(MovieDTO movieDTO) {
        Objects.requireNonNull(movieDTO, "movie cannot be null");
        Movie movie1 = new Movie(movieDTO.getMovie_title(),movieDTO.getProduction_year() ,movieDTO.getDirector(),movieDTO.getGenre(),movieDTO.getPrice(), employeeService.getEmployeeByName(movieDTO.getEmployee_name()), Calendar.getInstance().getTime());
        return movieRepository.save(movie1);
    }

    // update movie
    public Movie updateMovie(Integer id, MovieDTO movieDTO) {
            if (movieRepository.existsById(id)) {
                Movie movie = movieRepository.findById(id).get();
                movie.setMovie_title(movieDTO.getMovie_title());
                movie.setDirector(movieDTO.getDirector());
                movie.setGenre(movieDTO.getGenre());
                movie.setProduction_year(movieDTO.getProduction_year());
                movie.setPrice(movieDTO.getPrice());
                movie.setEmployee(employeeService.getEmployeeByName(movieDTO.getEmployee_name()));
                movie.setProduction_year(movieDTO.getProduction_year());
                return movieRepository.save(movie);
            }else {
                throw new MovieNotFoundException("Movie with id " + id + " is not found");
            }
    }

    public String deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie != null){
            movie.getFavoritedCustomers().forEach(customer -> customer.getFavorites().remove(movie));
            movieRepository.deleteById(id);
            deletedMovieRepository.save(new DeletedMovie(movie.getMovie_id(),movie.getMovie_title() ,movie.getProduction_year(), movie.getDirector(), movie.getAvg_rating(), movie.getGenre(), movie.getPrice(), movie.getEmployee(),movie.getMovie_register_date(),employeeService.getEmployeeByName(movie.getEmployee().getUser_name()) ,Calendar.getInstance().getTime()));
            return "Movie with id " + id + " is deleted";
        }else{
            throw new MovieNotFoundException("Movie with id " + id + " is not found");
        }

    }

    public List<Movie> getEmployeeRegisteredMovies(String employee_name){
        List<Movie> movies = movieRepository.findByEmployeeName(employee_name);
        for(Movie movie : movieRepository.findByEmployeeName(employee_name)){
            Double averageRating = movieReviewRepository.getAverageRating(movie.getMovie_id());
            if(averageRating != null)
                movie.setAvg_rating(averageRating);
        }
        return movies;
    }

    public List<Customer> getCustomerRents(Integer movie_id){
        Movie movie = movieRepository.findById(movie_id).orElse(null);
        if(movie == null)
            throw new MovieNotFoundException("Movie with id " + movie_id + " is not found");
        List<Rent> rents =  movie.getCustomerRents();
        List<Customer> customers = new ArrayList<>();
        for(Rent rent : rents) {
            customers.add(rent.getCustomer());
        }
        return customers;
    }

    public List<DeletedMovie> getAllDeletedMovies(){
        return deletedMovieRepository.findAll();
    }

    public DeletedMovie findDeletedMovieById(Integer id){
        return deletedMovieRepository.findById(id).orElse(null);
    }
}
