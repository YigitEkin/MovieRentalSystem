package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.dto.MovieReviewDTO;
import com.movie_rental_system.backend.service.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie_reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieReviewController {
    private final MovieReviewService movieReviewService;

    @Autowired
    public MovieReviewController(MovieReviewService movieReviewService) {
        this.movieReviewService = movieReviewService;
    }

    // get all movie reviews
    @GetMapping
    public ResponseEntity<List<MovieReviewDTO>> getAllMovieReviews() {
        return ResponseEntity.ok(MovieReviewDTO.toMovieReviewDTOList(movieReviewService.getAllMovieReviews()));
    }

    // get specific movie review
    @GetMapping("/{id}")
    public ResponseEntity<MovieReviewDTO> getMovieReview(@PathVariable Long id) {
        return ResponseEntity.ok(new MovieReviewDTO(movieReviewService.getMovieReviewById(id)));
    }

    // create movie review
    @PostMapping()
    public ResponseEntity<MovieReviewDTO> createMovieReview(@RequestBody MovieReviewDTO movieReviewDTO) {
        return ResponseEntity.ok(new MovieReviewDTO(movieReviewService.createMovieReview(movieReviewDTO)));
    }

    // update movie review
    @PutMapping("/{id}")
    public ResponseEntity<MovieReviewDTO> updateMovieReview(@PathVariable Long id, @RequestBody MovieReviewDTO movieReviewDTO) {
        return ResponseEntity.ok(new MovieReviewDTO(movieReviewService.updateMovieReview(id, movieReviewDTO)));
    }

    // delete movie review
    @DeleteMapping("/{id}")
    public ResponseEntity<MovieReviewDTO> deleteMovieReview(@PathVariable Long id) {
        return ResponseEntity.ok(new MovieReviewDTO(movieReviewService.deleteMovieReview(id)));
    }
}
