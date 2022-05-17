package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.*;
import com.movie_rental_system.backend.exception.*;
import com.movie_rental_system.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final NewCustomerRepository newCustomerRepository;
    private final RepeatCustomerRepository repeatCustomerRepository;
    private final MovieRepository movieRepository;
    private final MovieReviewRepository movieReviewRepository;

    @Autowired
    public CustomerService(UserRepository userRepository, CustomerRepository customerRepository, NewCustomerRepository newCustomerRepository, RepeatCustomerRepository repeatCustomerRepository, MovieRepository movieRepository, MovieReviewRepository movieReviewRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.newCustomerRepository = newCustomerRepository;
        this.repeatCustomerRepository = repeatCustomerRepository;
        this.movieRepository = movieRepository;
        this.movieReviewRepository = movieReviewRepository;
    }

    // adds a new customer to the database (all customers are new customers by default)
    public Customer createCustomer(NewCustomer newCustomer) {
        if (userRepository.existsById(newCustomer.getUser_name()))
            throw new UserAlreadyExistsException("User with name " + newCustomer.getUser_name() + " already exists");
        newCustomer.setBalance(new Double(100));
        return newCustomerRepository.save(newCustomer);
    }

    // find by customer_name
    public Customer findCustomer(String customer_name) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        return customer;
    }

    // removes the customer from new customer table and adds it to the repeat customer table
    // exception handling needed, relations of new customer is lost
    public Customer promoteCustomer(String user_name, String customer_rank, String discount_code) {
        NewCustomer newCustomer = newCustomerRepository.findById(user_name).get();
        userRepository.deleteById(user_name);
        repeatCustomerRepository.save(new RepeatCustomer(newCustomer.getUser_name(), newCustomer.getPassword(),
                newCustomer.getUser_email(), newCustomer.getBirth_year(), newCustomer.getBalance(), customer_rank, discount_code));
        return repeatCustomerRepository.findById(user_name).get();
    }

    // delete customer
    public Customer deleteCustomer(String user_name) {
        Customer customer = customerRepository.findById(user_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + user_name + " not found");
        // remove favorite movies
        customer.getFavorites().clear();
        userRepository.deleteById(user_name);
        return customer;
    }

    // update customer
    public Customer updateCustomer(String customer_name, Map<String, String> json) {
        try {
            if(newCustomerRepository.existsById(customer_name)){
                return newCustomerRepository.save(new NewCustomer(customer_name, json.get("password"), json.get("user_email"),
                        new SimpleDateFormat("yyyy-MM-dd").parse(json.get("birth_year")), Integer.parseInt(json.get("balance")), json.get("promotion_code")));
            }
            else if(repeatCustomerRepository.existsById(customer_name)){
                return repeatCustomerRepository.save(new RepeatCustomer(customer_name, json.get("password"), json.get("user_email"),
                        new SimpleDateFormat("yyyy-MM-dd").parse(json.get("birth_year")), Integer.parseInt(json.get("balance")), json.get("customer_rank"), json.get("discount_code")));
            }
            else
                throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        } catch (java.text.ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + json.get("birth_year") + " should be yyyy-MM-dd");
        }
    }

    // get all customers
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    // ---------------------Favorite Movie Methods---------------------

    // get favorite movies of customer
    public List<Movie> getFavoriteMovies(String customer_name) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        return customer.getFavorites();
    }

    // add favorite movie to customer
    public Movie addFavoriteMovie(String customer_name, Integer movie_id) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        Movie movie = movieRepository.findById(movie_id).orElse(null);
        if (movie == null)
            throw new MovieNotFoundException("Movie with id " + movie_id + " not found");
        if(customer.getFavorites().contains(movie))
            throw new FavoriteAlreadyExistsException("Movie with id " + movie_id + " already exists in customer's favorites");
        customer.getFavorites().add(movie);
        customerRepository.save(customer);
        return movie;
    }

    // remove favorite movie from customer
    public Movie removeFavoriteMovie(String customer_name, Integer movie_id) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        Movie movie = movieRepository.findById(movie_id).orElse(null);
        if (movie == null)
            throw new MovieNotFoundException("Movie with id " + movie_id + " not found");
        customer.getFavorites().remove(movie);
        customerRepository.save(customer);
        return movie;
    }

    public List<Movie> getRentedMovies(String customer_name) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        List<Movie> movies = customer.getRentedMoviesM();
        for(Movie movie : movies){
            Double averageRating = movieReviewRepository.getAverageRating(movie.getMovie_id());
            if(averageRating != null)
                movie.setAvg_rating(averageRating);
        }
        return customer.getRentedMoviesM();
    }

    public Movie rentMovie(String customer_name, Integer movie_id) {
        Customer customer = customerRepository.findById(customer_name).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        Movie movie = movieRepository.findById(movie_id).orElse(null);
        if (movie == null)
            throw new MovieNotFoundException("Movie with id " + movie_id + " not found");
        if(customer.getRentedMoviesM().contains(movie)){
            // is the rent expired?
            if (customer.checkExpiry(movie_id)) {
                customer.getRentedMoviesM().remove(movie);
                customerRepository.save(customer);
            }else
                // if the rent date is older than 1 month, the movie is considered as overdue and it is returned
            throw new AlreadyRentedException("Movie with id " + movie_id + " already exists in customer's rented movies");
        }
        customer.getRentedMovies().add(new Rent(customer, movie));
        customer.setBalance((customer.getBalance() - movie.getPrice()));
        System.out.println(customer.getBalance());
        customerRepository.save(customer);
        return movie;
    }
}
