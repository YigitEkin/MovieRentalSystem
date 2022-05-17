package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.dto.CardDTO;
import com.movie_rental_system.backend.dto.FriendDTO;
import com.movie_rental_system.backend.dto.MovieReviewDTO;
import com.movie_rental_system.backend.dto.RecommendDTO;
import com.movie_rental_system.backend.entity.*;
import com.movie_rental_system.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    private final CustomerService customerService;
    private final FriendService friendService;
    private final CardService cardService;
    private final MovieRequestService movieRequestService;
    private final MovieReviewService movieReviewService;
    private final RecommendService recommendService;

    @Autowired
    public CustomerController(CustomerService customerService, FriendService friendService, CardService cardService, MovieRequestService movieRequestService, MovieReviewService movieReviewService, RecommendService recommendService) {
        this.customerService = customerService;
        this.friendService = friendService;
        this.cardService = cardService;
        this.movieRequestService = movieRequestService;
        this.movieReviewService = movieReviewService;
        this.recommendService = recommendService;
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
    // example request body if new customer: same as create customer
    // example request body if repeat customer:
    /*{
        "user_name": "efe ertürk",
        "password": "customer password",
        "user_email": "customer email",
        "birth_year": "2022-05-20",
        "balance": 500,
        "customer_rank": "random rank",
        "discount_code": "discount code"
    } */
    @PutMapping("/{customer_name}")
    // you cant change customer name
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customer_name, @RequestBody Map<String, String> json) {
        return ResponseEntity.ok(customerService.updateCustomer(customer_name, json));
    }

    // create customer (new customer fields required since customer is new customer by default)
    // example request body:
    /* {
            "user_name": "user231",
            "password": "customer password",
            "user_email": "customer email",
            "birth_year": "2022-05-20",
            "balance": 0,
            "promotion_code": "aaaa"
    } */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody NewCustomer newCustomer) {
        return ResponseEntity.ok(customerService.createCustomer(newCustomer));
    }

    // delete customer
    @CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<List<FriendDTO>> getFriends(@PathVariable String customer_name) {
        return ResponseEntity.ok(friendService.getFriendsOfCustomer(customer_name));
    }

    // get specific friend of a customer
    @GetMapping("/{customer_name}/friends/{friend_name}")
    public ResponseEntity<FriendDTO> getFriend(@PathVariable String customer_name, @PathVariable String friend_name) {
        return ResponseEntity.ok(friendService.getFriend(customer_name, friend_name));
    }

    // delete friend of a customer
    @DeleteMapping("/{customer_name}/friends/{friend_name}")
    public ResponseEntity<FriendDTO> deleteFriend(@PathVariable String customer_name, @PathVariable String friend_name) {
        return ResponseEntity.ok(friendService.deleteFriend(customer_name, friend_name));
    }

    // update friend of a customer
    // example request body: same as create friend in friend controller
    @PutMapping("/{customer_name}/friends/{friend_name}")
    public ResponseEntity<FriendDTO> updateFriend(@PathVariable String customer_name, @PathVariable String friend_name, @RequestBody FriendDTO friendDTO) {
        return ResponseEntity.ok(friendService.updateFriend(customer_name, friend_name, friendDTO));
    }


    // -----------------card endpoints------------------
    // get all cards of a customer
    @GetMapping("/{customer_name}/cards")
    public ResponseEntity<List<CardDTO>> getCards(@PathVariable String customer_name) {
        return ResponseEntity.ok(CardDTO.toCardDTOList(cardService.getCardsOfCustomer(customer_name)));
    }

    // get specific card of a customer
    @GetMapping("/{customer_name}/cards/{card_id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable String customer_name, @PathVariable Long card_id) {
        return ResponseEntity.ok(new CardDTO(cardService.getCardOfCustomer(customer_name, card_id)));
    }


    // -----------------movie request endpoints------------------
    // get all movie requests of a customer
    @GetMapping("/{customer_name}/movie_requests")
    public ResponseEntity<List<MovieRequest>> getMovieRequests(@PathVariable String customer_name) {
        return ResponseEntity.ok(movieRequestService.getRequestsOfCustomer(customer_name));
    }


    // -----------------movie review endpoints------------------
    // get all movie reviews of a customer
    @GetMapping("/{customer_name}/movie_reviews")
    public ResponseEntity<List<MovieReviewDTO>> getMovieReviews(@PathVariable String customer_name) {
        return ResponseEntity.ok(MovieReviewDTO.toMovieReviewDTOList(movieReviewService.getMovieReviewsByCustomerName(customer_name)));
    }

    // -----------------movie favorite endpoints------------------
    // get all movie favorites of a customer
    @GetMapping("/{customer_name}/favorites")
    public ResponseEntity<List<Movie>> getMovieFavorites(@PathVariable String customer_name) {
        return ResponseEntity.ok(customerService.getFavoriteMovies(customer_name));
    }

    // create movie favorite of a customer
    @PostMapping("/{customer_name}/favorites/{movie_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Movie> createMovieFavorite(@PathVariable String customer_name, @PathVariable Integer movie_id) {
        return ResponseEntity.ok(customerService.addFavoriteMovie(customer_name, movie_id));
    }

    // delete movie favorite of a customer
    @DeleteMapping("/{customer_name}/favorites/{movie_id}")
    public ResponseEntity<Movie> deleteMovieFavorite(@PathVariable String customer_name, @PathVariable Integer movie_id) {
        return ResponseEntity.ok(customerService.removeFavoriteMovie(customer_name, movie_id));
    }
    // -----------------rent movie endpoints------------------
    // get all rented movies of a customer
    @GetMapping("/{customer_name}/rents")
    public ResponseEntity<List<Movie>> getRentedMovies(@PathVariable String customer_name) {
        return ResponseEntity.ok(customerService.getRentedMovies(customer_name));
    }

    // rent a movie
    @PostMapping("/{customer_name}/rents/{movie_id}")
    public ResponseEntity<Movie> rentMovie(@PathVariable String customer_name, @PathVariable Integer movie_id) {
        return ResponseEntity.ok(customerService.rentMovie(customer_name, movie_id));
    }

    // -----------------recommend endpoints------------------

    // get recommends where customer is recommender
    @GetMapping("/{customer_name}/recommender")
    public ResponseEntity<List<RecommendDTO>> getRecommends(@PathVariable String customer_name) {
        return ResponseEntity.ok(RecommendDTO.toRecommendDTOList(recommendService.getRecommendByRecommender(customer_name)));
    }

    // get recommends where customer is recommended
    @GetMapping("/{customer_name}/recommended")
    public ResponseEntity<List<RecommendDTO>> getRecommended(@PathVariable String customer_name) {
        return ResponseEntity.ok(RecommendDTO.toRecommendDTOList(recommendService.getRecommendByRecommended(customer_name)));
    }


    // unrent a movie ???
}
