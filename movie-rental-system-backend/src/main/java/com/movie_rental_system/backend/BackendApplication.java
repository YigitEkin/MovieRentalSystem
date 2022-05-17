package com.movie_rental_system.backend;

import com.movie_rental_system.backend.entity.Actor;
import com.movie_rental_system.backend.entity.Employee;
import com.movie_rental_system.backend.entity.User;
import com.movie_rental_system.backend.repository.ActorRepository;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.EmployeeRepository;
import com.movie_rental_system.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class BackendApplication {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner lineRunner(EmployeeRepository employeeRepository, ActorRepository actorRepository, CustomerRepository customerRepository ){
        return args -> {
            employeeRepository.save(new Employee("employee", "1234", "aa", Calendar.getInstance().getTime(), 1000));
            actorRepository.save(new Actor("cem yılmaz", 1970));
            actorRepository.save(new Actor("actor temp", 1970));
            actorRepository.save(new Actor("actor temp2", 1970));
        };
    }

}
