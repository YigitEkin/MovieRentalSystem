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

    // create friend
    // json example: {"customer_name":"customer_name", "friend_name":"friend_name", "friend_request_date": "2020-01-01"}
    @PostMapping
    public ResponseEntity<Friend> createFriend(@RequestBody Map<String, String> json) {
        return ResponseEntity.ok(friendService.addFriend(json));
    }
}
