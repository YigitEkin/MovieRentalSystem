package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Friend;
import com.movie_rental_system.backend.entity.NewCustomer;
import com.movie_rental_system.backend.service.CustomerService;
import com.movie_rental_system.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final FriendService friendService;

    @Autowired
    public CustomerController(CustomerService customerService, FriendService friendService) {
        this.customerService = customerService;
        this.friendService = friendService;
    }

    // -----------------customer endpoints------------------
    // get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    // find customer by name
    @GetMapping("/{customer_name}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customer_name) {
        System.out.println(customer_name);
        return ResponseEntity.ok(customerService.findCustomer(customer_name));
    }

    // update customer
    @PutMapping("/{customer_name}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customer_name, @RequestBody Map<String, String> json) {
        return ResponseEntity.ok(customerService.updateCustomer(customer_name, json));
    }

    // create customer (new customer fields required since customer is new customer by default)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody NewCustomer newCustomer) {
        return ResponseEntity.ok(customerService.createCustomer(newCustomer));
    }

    // delete customer
    @DeleteMapping("/{customer_name}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String customer_name) {
        return ResponseEntity.ok(customerService.deleteCustomer(customer_name));
    }

    // promote customer to repeat customer
    @PatchMapping("/promote/{customer_name}")
    public ResponseEntity<Customer> promoteCustomer(@PathVariable String customer_name) {
        return ResponseEntity.ok(customerService.promoteCustomer(customer_name, "random rank", "discount code"));
    }


    // -----------------friend endpoints------------------
    // get all friends of a customer
    @GetMapping("/{customer_name}/friends")
    public ResponseEntity<List<Friend>> getFriends(@PathVariable String customer_name) {
        return ResponseEntity.ok(friendService.getFriendsOfCustomer(customer_name));
    }

    // get specific friend of a customer
    @GetMapping("/{customer_name}/friends/{friend_name}")
    public ResponseEntity<Friend> getFriend(@PathVariable String customer_name, @PathVariable String friend_name) {
        return ResponseEntity.ok(friendService.getFriend(customer_name, friend_name));
    }

    // delete friend of a customer
    @DeleteMapping("/{customer_name}/friends/{friend_name}")
    public ResponseEntity<Friend> deleteFriend(@PathVariable String customer_name, @PathVariable String friend_name) {
        return ResponseEntity.ok(friendService.deleteFriend(customer_name, friend_name));
    }

    // update friend of a customer
    @PutMapping("/{customer_name}/friends/{friend_name}")
    public ResponseEntity<Friend> updateFriend(@PathVariable String customer_name, @PathVariable String friend_name, @RequestBody Map<String, String> json) {
        return ResponseEntity.ok(friendService.updateFriend(customer_name, friend_name, json));
    }

}
