package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;
    @NonNull
    private String movie_title;
    private int production_year;
    private String director;
    private double avg_rating; //?
    private String genre;
    private double price;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "employee_name",nullable=false)
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "salary"})
    private Employee employee;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovieReview> movieReviews;

    @ManyToMany(mappedBy = "favorites", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Customer> favoritedCustomers;

    private Date movie_register_date;

    public Movie(String movie_title, int production_year, String director, String genre, double price, Employee employee, Date time) {
        this.movie_title = movie_title;
        this.production_year = production_year;
        this.director = director;
        //this.avg_rating = avg_rating;
        this.genre = genre;
        this.price = price;
        this.employee = employee;
        this.movie_register_date = time;
    }
}

