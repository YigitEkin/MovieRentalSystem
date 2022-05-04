package com.movie_rental_system.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "employee_name")
public class Employee extends User {
    private Integer salary;

    public Employee(String user_name, String password, String user_email, Date birth_year, Integer salary) {
        super(user_name, password, user_email, birth_year);
        this.salary = salary;
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Movie> registered_Movies;
}
