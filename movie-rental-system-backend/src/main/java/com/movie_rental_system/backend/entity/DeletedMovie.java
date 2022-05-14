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
@Table(name = "deleted_movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletedMovie implements Serializable {
    @Id
    private int movie_id;
    @NonNull
    private String movie_title;
    private int production_year;
    private String director;
    private double avg_rating; //?
    private String genre;
    private double price;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "adder_employee",nullable=false)
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "salary"})
    private Employee adderEmployee;

    private Date movie_register_date;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "remover_employee",nullable=false)
    @JsonIgnoreProperties({"password", "user_email", "birth_year", "salary"})
    private Employee removerEmployee;

    private Date movie_deletion_date;
}

