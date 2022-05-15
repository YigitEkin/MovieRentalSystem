package com.movie_rental_system.backend.util;

import com.movie_rental_system.backend.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
// class created for composite primary key of friend entity
public class FriendKey implements Serializable {
    private Customer customer;
    private Customer friend;
}
