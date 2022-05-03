package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Card;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.User;
import com.movie_rental_system.backend.repository.MovieRepository;
import com.movie_rental_system.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository repo){
        this.movieRepository = repo;
    }

    public List<Movie> getAll(){
        return movieRepository.getAll();
    }

    public Movie findByMovieId(Integer id){
        return movieRepository.findById(id).get();
    }

    public Movie addNewMovie(Movie movie) {
        Objects.requireNonNull(movie, "movie cannot be null");
        return movieRepository.save(movie);
    }

    public String deleteMovie(Integer id) {
        movieRepository.deleteById(id);
        return "Movie is successfully deleted";
    }
}
