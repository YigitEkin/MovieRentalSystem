package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.dto.FriendDTO;
import com.movie_rental_system.backend.entity.Customer;
import com.movie_rental_system.backend.entity.Friend;
import com.movie_rental_system.backend.exception.*;
import com.movie_rental_system.backend.repository.CustomerRepository;
import com.movie_rental_system.backend.repository.FriendRepository;
import com.movie_rental_system.backend.util.FriendKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public List<FriendDTO> getAllFriends() {
        return FriendDTO.toFriendDTOList(friendRepository.findAll());
    }

    public FriendDTO getFriend(String customer_name, String friend_name) {
        if (!customerRepository.existsById(customer_name) )
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        else if (!customerRepository.existsById(friend_name))
            throw new CustomerNotFoundException("Customer with name " + friend_name + " not found");
        Friend friend = friendRepository.findFriend(customer_name, friend_name);
        if (friend == null)
            throw new FriendNotFoundException("Friend between " + customer_name + " and " + friend_name + " not found");
        return new FriendDTO(friend);
    }

    // get friends of customer
    public List<FriendDTO> getFriendsOfCustomer(String customer_name) {
        if (!customerRepository.existsById(customer_name))
            throw new CustomerNotFoundException("Customer with name " + customer_name + " not found");
        return FriendDTO.toFriendDTOList(friendRepository.findFriendsOfCustomer(customer_name));
    }

    // add a friend
    public FriendDTO addFriend(FriendDTO friendDTO) {
        Customer customer1 = customerRepository.findById(friendDTO.getCustomer_name()).orElse(null);
        Customer customer2 = customerRepository.findById(friendDTO.getFriend_name()).orElse(null);

        if (customer1 == null)
            throw new CustomerNotFoundException("Customer with name " + friendDTO.getCustomer_name() + " not found");
        if (customer2 == null)
            throw new CustomerNotFoundException("Customer with name " + friendDTO.getFriend_name() + " not found");
        if (customer1.getUser_name().equals(customer2.getUser_name()))
            throw new InvalidFriendRequestException("Customer with name " + friendDTO.getCustomer_name() + " is the same as customer with name " + friendDTO.getFriend_name());
        if(friendRepository.findFriend( friendDTO.getCustomer_name(), friendDTO.getFriend_name()) != null)
            throw new FriendAlreadyExistsException("Friend between " + friendDTO.getCustomer_name() + " and " + friendDTO.getFriend_name() + " already exists");

        try {
            Friend friend = new Friend(customer1, customer2, new SimpleDateFormat("yyyy-MM-dd").parse(friendDTO.getFriend_request_date()));
            return new FriendDTO(friendRepository.save(friend));
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + friendDTO.getFriend_request_date() + " should be yyyy-MM-dd");
        }
    }

    // delete a friend
    public FriendDTO deleteFriend(String customer_name, String friend_name) {
        Friend friend = friendRepository.findFriend(friend_name, customer_name);
        if(friend == null)
            throw new FriendNotFoundException("Friend between " + customer_name + " and " + friend_name + " not found");
        friendRepository.deleteFriend(customer_name, friend_name);
        return new FriendDTO(friend);
    }

    // update a friend
    public FriendDTO updateFriend(String customer_name, String friend_name, FriendDTO friendDTO) {
        Friend friend = friendRepository.findFriend(friend_name, customer_name);
        if (friend == null)
            throw new FriendNotFoundException("Friend between " + customer_name + " and " + friend_name + " not found");

        try {
            friend.setFriend_request_date(new SimpleDateFormat("yyyy-MM-dd").parse(friendDTO.getFriend_request_date()));
            return new FriendDTO(friendRepository.save(friend));
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + friendDTO.getFriend_request_date() + " should be yyyy-MM-dd");
        }
    }
}
