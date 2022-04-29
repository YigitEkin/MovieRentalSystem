package com.movie_rental_system.backend;

import com.movie_rental_system.backend.entity.User;
import com.movie_rental_system.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner lineRunner(UserRepository userRepository){
        return args -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2019, 12, 30);
            userRepository.saveAll(Arrays.asList(
                    new User("user1", "password1", "email1@gmail.com", calendar.getTime())
            ));
        };
    }

}
