package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.dto.MovieRequestDTO;
import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Movie;
import com.movie_rental_system.backend.entity.MovieRequest;
import com.movie_rental_system.backend.entity.User;
import com.movie_rental_system.backend.service.MovieRequestService;
import com.movie_rental_system.backend.service.MovieService;
import com.movie_rental_system.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie_requests")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieRequestController {

    private final MovieRequestService movieRequestService;

    @Autowired
    public MovieRequestController(MovieRequestService movieService){
        this.movieRequestService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieRequest>> getAllMovieRequests(){
        return ResponseEntity.ok(movieRequestService.getAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieRequest> getMovieRequestById(@PathVariable Integer id){
        return ResponseEntity.ok(movieRequestService.findRequestById(id));
    }

    @PostMapping
    public ResponseEntity<?> createMovieRequest(@RequestBody MovieRequestDTO movieRequest){
        return ResponseEntity.ok(movieRequestService.creatMovieRequest(movieRequest));
    }

    // delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieRequest(@PathVariable Integer id) {
        return ResponseEntity.ok(movieRequestService.deleteMovieRequest(id));
    }
}
