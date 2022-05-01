package com.movie_rental_system.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;
    @NonNull
    private String movie_title;
    private int production_year;
    private String director;
    private String avg_rating; //?
    private String genre;
    private double price;
}
