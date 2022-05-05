package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.MovieDTO;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.repository.MovieRepository;
import com.movie_rental_system.backend.repository.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
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

    public String deleteMovie(Integer id) {
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
