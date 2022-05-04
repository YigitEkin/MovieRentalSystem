package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.NewCustomer;
import com.movie_rental_system.backend.entity.RepeatCustomer;
import com.movie_rental_system.backend.exception.CustomerNotFoundException;
import com.movie_rental_system.backend.exception.InvalidDateFormatException;
import com.movie_rental_system.backend.exception.UserAlreadyExistsException;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.NewCustomerRepository;
import com.movie_rental_system.backend.repository.RepeatCustomerRepository;
import com.movie_rental_system.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final NewCustomerRepository newCustomerRepository;
    private final RepeatCustomerRepository repeatCustomerRepository;

    @Autowired
    public CustomerService(UserRepository userRepository, CustomerRepository customerRepository, NewCustomerRepository newCustomerRepository, RepeatCustomerRepository repeatCustomerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.newCustomerRepository = newCustomerRepository;
        this.repeatCustomerRepository = repeatCustomerRepository;
    }


    // adds a new customer to the database (all customers are new customers by default)
    public Customer createCustomer(NewCustomer newCustomer) {
        if (userRepository.existsById(newCustomer.getUser_name()))
            throw new UserAlreadyExistsException("User with name " + newCustomer.getUser_name() + " already exists");
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
}
