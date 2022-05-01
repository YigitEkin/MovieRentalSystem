package com.movie_rental_system.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
@PrimaryKeyJoinColumn(name = "customer_name")
@NoArgsConstructor
public class Customer extends User {
    private Integer balance;

    public Customer(String user_name, String password, String email, Date birthday, Integer balance) {
        super(user_name, password, email, birthday);
        this.balance = balance;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.balance;
    }
}

