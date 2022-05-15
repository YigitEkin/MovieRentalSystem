package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "movie_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int request_id;
    @NonNull
    private String movie_title;
    private int production_year;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_name")
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "balance", "discount_code", "customer_rank", "promotion_code"})
    private Customer customer;
}
