package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Friend;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
        return friendRepository.findFriend(customer_name, friend_name);
    }

    // get friends of customer
    public List<Friend> getFriendsOfCustomer(String customer_name) {
        return friendRepository.findFriendsOfCustomer(customer_name);
    }

    // add a friend
    public Friend addFriend(Map<String, String> json) {
        Customer customer1 = customerRepository.findById(json.get("customer_name")).get();
        Customer customer2 = customerRepository.findById(json.get("friend_name")).get();

        try {
            Friend friend = new Friend(customer1, customer2, new SimpleDateFormat("yyyy-MM-dd").parse(json.get("friend_request_date")));
            return friendRepository.save(friend);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // delete a friend
    public Friend deleteFriend(String customer_name, String friend_name) {
        Friend friend = friendRepository.findFriend(friend_name, customer_name);
        friendRepository.deleteFriend(customer_name, friend_name);
        return friend;
    }

    // update a friend
    public Friend updateFriend(String customer_name, String friend_name, Map<String, String> json) {
        Friend friend1 = friendRepository.findFriend(customer_name, friend_name);
        friendRepository.delete(friend1);
        try {
            return friendRepository.save(new Friend(customerRepository.findById(customer_name).get(), customerRepository.findById(friend_name).get(),
                    new SimpleDateFormat("yyyy-MM-dd").parse(json.get("friend_request_date"))));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
