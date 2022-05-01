package com.movie_rental_system.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "repeat_customer")
@PrimaryKeyJoinColumn(name = "customer_name")
public class RepeatCustomer extends Customer {
    private String customer_rank;
    private String discount_code;

    public RepeatCustomer(String user_name, String password, String email, Date birthday, Integer balance, String customer_rank, String discount_code) {
        super(user_name, password, email, birthday, balance);
        this.customer_rank = customer_rank;
        this.discount_code = discount_code;
    }
}
