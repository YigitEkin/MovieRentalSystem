package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.dto.MovieDTO;
import com.movie_rental_system.backend.dto.MovieReviewDTO;
import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.User;
import com.movie_rental_system.backend.service.MovieReviewService;
import com.movie_rental_system.backend.service.MovieService;
import com.movie_rental_system.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieReviewService movieReviewService;

    @Autowired
    public MovieController(MovieService movieService, MovieReviewService movieReviewService) {
        this.movieService = movieService;
        this.movieReviewService = movieReviewService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id){
        return ResponseEntity.ok(movieService.findByMovieId(id));
    }

    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody MovieDTO movie){
        return ResponseEntity.ok(movieService.addNewMovie(movie));
    }

    // delete movie
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }

    // --------------Movie Review Enpoints-----------------

    // get movie reviews
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<MovieReviewDTO>> getMovieReviews(@PathVariable Integer id){
        return ResponseEntity.ok(MovieReviewDTO.toMovieReviewDTOList(movieReviewService.getMovieReviewsByMovieId(id)));
    }


}