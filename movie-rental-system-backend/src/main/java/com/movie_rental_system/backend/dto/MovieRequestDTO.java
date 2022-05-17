package com.movie_rental_system.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movie_rental_system.backend.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class MovieRequestDTO {
    private int request_id;
    private String movie_title;
    private int production_year;
    private String customer_name;
    private String description;
}
