package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movie_rental_system.backend.util.FriendKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "friend")
@IdClass(FriendKey.class)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id"})
public class Friend implements Persistable<FriendKey>{

    public Friend(Customer customer, Customer friend, Date friend_request_date) {
        this.customer = customer;
        this.friend = friend;
        this.friend_request_date = friend_request_date;
    }

    @Transient
    @JsonIgnore
    private boolean isNew = false;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @Id
    @MapsId
    @JoinColumn(name = "customer_name")
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "balance", "discount_code", "customer_rank", "promotion_code"})
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    @Id
    @JoinColumn(name = "friend_name")
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "balance", "discount_code", "customer_rank", "promotion_code"})
    private Customer friend;

    @Temporal(TemporalType.DATE)
    private Date friend_request_date;

    @Override
    public FriendKey getId() {
        return new FriendKey(customer, friend);
    }

    @Override
    public boolean isNew() {
        return isNew;
    }




}
