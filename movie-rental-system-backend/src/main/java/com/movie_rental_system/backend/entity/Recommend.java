package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recommend")
@NoArgsConstructor
@AllArgsConstructor
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recommend_id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "recommended_user_name")
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "balance", "discount_code", "customer_rank", "promotion_code"})
    private Customer recommended_user_name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "recommender_user_name")
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "balance", "discount_code", "customer_rank", "promotion_code"})
    private Customer recommender_user_name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "movie_id")
    private Movie movie_id;

    private String message;
}
