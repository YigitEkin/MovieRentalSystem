package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movie_rental_system.backend.util.FriendKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "rent")
@AllArgsConstructor
@NoArgsConstructor
public class Rent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rent_id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "customer_name")
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "balance", "discount_code", "customer_rank", "promotion_code"})
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Temporal(TemporalType.DATE)
    private Date rent_date;

    public Rent(Customer customer, Movie movie){
        this.customer = customer;
        this.movie = movie;
        this.rent_date = Calendar.getInstance().getTime();
    }


}
