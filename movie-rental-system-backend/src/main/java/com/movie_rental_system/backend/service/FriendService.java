package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Friend;
import com.movie_rental_system.backend.exception.CustomerNotFoundException;
import com.movie_rental_system.backend.exception.FriendNotFoundException;
import com.movie_rental_system.backend.exception.InvalidDateFormatException;
import com.movie_rental_system.backend.exception.InvalidFriendRequestException;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository, CustomerRepository customerRepository) {
        this.friendRepository = friendRepository;
        this.customerRepository = customerRepository;
    }

    // get all friends
    public List<Friend> getAllFriends() {
        return friendRepository.findAll();
    }

    public Friend getFriend(String customer_name, String friend_name) {
        if (!customerRepository.existsById(customer_name) )
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        else if (!customerRepository.existsById(friend_name))
            throw new CustomerNotFoundException("Customer with name " + friend_name + " not found");
        Friend friend = friendRepository.findFriend(customer_name, friend_name);
        if (friend == null)
            throw new FriendNotFoundException("Friend between " + customer_name + " and " + friend_name + " not found");
        return friend;
    }

    // get friends of customer
    public List<Friend> getFriendsOfCustomer(String customer_name) {
        if (!customerRepository.existsById(customer_name))
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        return friendRepository.findFriendsOfCustomer(customer_name);
    }

    // add a friend
    public Friend addFriend(Map<String, String> json) {
        Customer customer1 = customerRepository.findById(json.get("customer_name")).orElse(null);
        Customer customer2 = customerRepository.findById(json.get("friend_name")).orElse(null);

        if (customer1 == null)
            throw new CustomerNotFoundException("Customer with name " + json.get("customer_name") + " not found");
        if (customer2 == null)
            throw new CustomerNotFoundException("Customer with name " + json.get("friend_name") + " not found");
        if (customer1.getUser_name().equals(customer2.getUser_name()))
            throw new InvalidFriendRequestException("Customer with name " + json.get("friend_name") + " is the same as customer with name " + json.get("customer_name"));

        try {
            Friend friend = new Friend(customer1, customer2, new SimpleDateFormat("yyyy-MM-dd").parse(json.get("friend_request_date")));
            return friendRepository.save(friend);
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + json.get("friend_request_date") + " should be yyyy-MM-dd");
        }
    }

    // delete a friend
    public Friend deleteFriend(String customer_name, String friend_name) {
        Friend friend = friendRepository.findFriend(friend_name, customer_name);
        if(friend == null)
            throw new FriendNotFoundException("Friend between " + customer_name + " and " + friend_name + " not found");
        friendRepository.deleteFriend(customer_name, friend_name);
        return friend;
    }

    // update a friend
    public Friend updateFriend(String customer_name, String friend_name, Map<String, String> json) {
        Friend friend = friendRepository.findFriend(friend_name, customer_name);
        if (friend == null)
            throw new FriendNotFoundException("Friend between " + customer_name + " and " + friend_name + " not found");

        try {
            friend.setFriend_request_date(new SimpleDateFormat("yyyy-MM-dd").parse(json.get("friend_request_date")));
            return friendRepository.save(friend);
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + json.get("friend_request_date") + " should be yyyy-MM-dd");
        }
    }
}
