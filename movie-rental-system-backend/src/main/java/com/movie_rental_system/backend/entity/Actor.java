package com.movie_rental_system.backend.entity;

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
@Table(name = "actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actor_id;
    @NonNull
    private String actor_name;
    private int birth_year;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "acts",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    List<Movie> movies;

    public Actor(String actor_name, int birth_year) {
        this.actor_name = actor_name;
        this.birth_year = birth_year;
    }
}