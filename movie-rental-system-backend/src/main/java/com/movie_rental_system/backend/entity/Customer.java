package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
@PrimaryKeyJoinColumn(name = "customer_name")
@NoArgsConstructor
public class Customer extends User {
    private Double balance;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friend> friends_customer;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friend> friends_friend;

    @OneToMany(mappedBy = "recommended_user_name", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Recommend> recommends_recommended;

    @OneToMany(mappedBy = "recommender_user_name", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Recommend> recommends_recommender;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Card> cards;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovieRequest> requests;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovieReview> movieReviews;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rent> rentedMovies;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite",
            joinColumns = @JoinColumn(name = "customer_name"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @JsonIgnore
    private List<Movie> favorites;

    public Customer(String user_name, String password, String email, Date birthday, double balance) {
        super(user_name, password, email, birthday);
        this.balance = balance;
    }

    public Customer(String user_name, String password, String email, Date birthday, double balance, List<Friend> friends, List<Card> cards, List<MovieRequest> requests) {
        super(user_name, password, email, birthday);
        this.balance = balance;
        this.friends_customer = friends;
        this.cards = cards;
        this.requests = requests;
    }


    @Override
    public String toString() {
        return super.toString() + " " + this.balance;
    }

    // returns the rented movies as a list of movies, not as a list of rents
    @JsonIgnore
    public List<Movie> getRentedMoviesM(){
        List<Movie> movies = new ArrayList<>();
        for (Rent rent : this.rentedMovies) {
            movies.add(rent.getMovie());
        }
        return movies;
    }

    @JsonIgnore
    public boolean checkExpiry(Integer movie_id){
        for (Rent rent : this.rentedMovies) {
            if (rent.getMovie().getMovie_id() == movie_id) {
                Calendar lastMonth = Calendar.getInstance();
                lastMonth.add(Calendar.MONTH, -1);
                if(lastMonth.getTime().after(rent.getRent_date())){
                    return true;
                }
            }
        }
        return false;
    }
}

