package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.MovieDTO;
import com.movie_rental_system.backend.entity.Card;
import com.movie_rental_system.backend.entity.Employee;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.User;
import com.movie_rental_system.backend.repository.MovieRepository;
import com.movie_rental_system.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final EmployeeService employeeService;

    @Autowired
    public MovieService(MovieRepository repo, EmployeeService employeeService){
        this.movieRepository = repo;
        this.employeeService = employeeService;
    }

    public List<Movie> getAll(){
        return movieRepository.getAll();
    }

    public Movie findByMovieId(Integer id){
        return movieRepository.findById(id).get();
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
        return movieRepository.findByEmployeeName(employee_name);
    }
}
