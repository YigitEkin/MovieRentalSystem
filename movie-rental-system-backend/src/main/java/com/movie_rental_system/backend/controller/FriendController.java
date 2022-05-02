package com.movie_rental_system.backend.controller;


import com.movie_rental_system.backend.entity.Friend;
import com.movie_rental_system.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // get all friends
    @GetMapping
    public ResponseEntity<List<Friend>> getAllFriends() {
        return ResponseEntity.ok(friendService.getAllFriends());
    }

    // get friends of a customer
    @GetMapping("/{customer_name}")
    public ResponseEntity<List<Friend>> getFriendsOfCustomer(@PathVariable String customer_name) {
        return ResponseEntity.ok(friendService.getFriendsOfCustomer(customer_name));
    }

    // get specific friend, order is not important
    @GetMapping("/{customer_name}/{friend_name}")
    public ResponseEntity<Friend> getFriend(@PathVariable String customer_name, @PathVariable String friend_name) {
        return ResponseEntity.ok(friendService.getFriend(customer_name, friend_name));
    }

    // delete friend, order is not important
    @DeleteMapping("/{customer_name}/{friend_name}")
    public ResponseEntity<Friend> deleteFriend(@PathVariable String customer_name, @PathVariable String friend_name) {
        return ResponseEntity.ok(friendService.deleteFriend(customer_name, friend_name));
    }

    // update friend, order is not important
    // json example: {"customer_name":"customer_name", "friend_name":"friend_name", "friend_request_date": "2020-01-01"}
    @PutMapping("/{customer_name}/{friend_name}")
    public ResponseEntity<Friend> updateFriend(@PathVariable String customer_name, @PathVariable String friend_name,
                                               @RequestBody Map<String,String> json) {
        return ResponseEntity.ok(friendService.updateFriend(customer_name, friend_name, json));
    }

    // create friend
    // json example: {"customer_name":"customer_name", "friend_name":"friend_name", "friend_request_date": "2020-01-01"}
    @PostMapping
    public ResponseEntity<Friend> createFriend(@RequestBody Map<String, String> json) {
        return ResponseEntity.ok(friendService.addFriend(json));
    }
}
