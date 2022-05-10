package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.MovieDTO;
import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.NewCustomer;
import com.movie_rental_system.backend.entity.RepeatCustomer;
import com.movie_rental_system.backend.exception.CustomerNotFoundException;
import com.movie_rental_system.backend.exception.InvalidDateFormatException;
import com.movie_rental_system.backend.exception.MovieNotFoundException;
import com.movie_rental_system.backend.repository.MovieRepository;
import com.movie_rental_system.backend.repository.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final EmployeeService employeeService;
    private final MovieReviewRepository movieReviewRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, EmployeeService employeeService, MovieReviewRepository movieReviewRepository) {
        this.movieRepository = movieRepository;
        this.employeeService = employeeService;
        this.movieReviewRepository = movieReviewRepository;
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
                return movieRepository.save(new Movie(movieDTO.getMovie_title(),movieDTO.getProduction_year() ,movieDTO.getDirector(),movieDTO.getGenre(),movieDTO.getPrice(), employeeService.getEmployeeByName(movieDTO.getEmployee_name()), Calendar.getInstance().getTime()));
            }else {
                throw new MovieNotFoundException("Movie with id " + id + " is not found");
            }
    }

    public String deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie != null)
            movie.getFavoritedCustomers().forEach(customer -> customer.getFavorites().remove(movie));
        movieRepository.deleteById(id);
        return "Movie is successfully deleted";
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
}
