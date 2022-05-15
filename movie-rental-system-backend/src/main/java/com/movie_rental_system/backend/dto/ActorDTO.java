package com.movie_rental_system.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActorDTO {
    private String actor_name;
    private int birth_year;
}
