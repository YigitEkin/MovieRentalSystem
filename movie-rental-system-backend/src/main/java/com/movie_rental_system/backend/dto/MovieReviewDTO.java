package com.movie_rental_system.backend.dto;

import com.movie_rental_system.backend.entity.MovieReview;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovieReviewDTO {
    private Long review_id;
    private Integer movie_id;
    private String customer_name;
    private Double rating;
    private String review_message;
    private boolean spoiler;
    private Integer net_like;
    private String review_date;

    public MovieReviewDTO(MovieReview movieReview) {
        this.review_id = movieReview.getReview_id();
        this.movie_id = movieReview.getMovie().getMovie_id();
        this.customer_name = movieReview.getCustomer().getUser_name();
        this.rating = movieReview.getRating();
        this.review_message = movieReview.getReview_message();
        this.spoiler = movieReview.isSpoiler();
        this.net_like = movieReview.getNet_like();
        this.review_date = movieReview.getReview_date().toString();
    }

    public static List<MovieReviewDTO> toMovieReviewDTOList(List<MovieReview> movieReviews) {
        return movieReviews.stream().map(MovieReviewDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
