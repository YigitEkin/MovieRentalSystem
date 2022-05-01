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
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "new_customer")
@PrimaryKeyJoinColumn(name = "customer_name")
public class NewCustomer extends Customer {
    private String promotion_code;

    public NewCustomer(String user_name, String password, String email, Date birthday, Integer balance, String promotion_code) {
        super(user_name, password, email, birthday, balance);
        this.promotion_code = promotion_code;
    }
}
