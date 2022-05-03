package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
@PrimaryKeyJoinColumn(name = "customer_name")
@NoArgsConstructor
public class Customer extends User {
    private Integer balance;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friend> friends;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Card> cards;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovieRequest> requests;

    public Customer(String user_name, String password, String email, Date birthday, Integer balance) {
        super(user_name, password, email, birthday);
        this.balance = balance;
    }

    public Customer(String user_name, String password, String email, Date birthday, Integer balance, List<Friend> friends, List<Card> cards) {
        super(user_name, password, email, birthday);
        this.balance = balance;
        this.friends = friends;
        this.cards = cards;
    }



    @Override
    public String toString() {
        return super.toString() + " " + this.balance;
    }
}

