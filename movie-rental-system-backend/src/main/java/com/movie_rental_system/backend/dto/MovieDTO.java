package com.movie_rental_system.backend.dto;

import com.movie_rental_system.backend.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class MovieDTO {

    private String movie_title;
    private int production_year;
    private String director;
    private String genre;
    private double price;

    private String employee_name;

    private List<Integer> actors;

    private String img_url;
}
