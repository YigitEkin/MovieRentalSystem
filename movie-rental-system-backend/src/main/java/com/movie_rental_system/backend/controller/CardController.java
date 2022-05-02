package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.entity.Card;
import com.movie_rental_system.backend.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    // get all cards
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    // create a card
    // example request body:
    /* {
        "customer_name": "user1",
        "card_number": "1322 1234 1234 1234",
        "exp_date": "12/25",
        "cvv": "023",
        "holder_name": "user1"
    } */
    @PostMapping()
    public ResponseEntity<Card> createCard(@RequestBody Map<String, String> json) {
        return ResponseEntity.ok(cardService.createCard(json));
    }

    // update a card
    // example request body: same as create card
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Map<String, String> json) {
        return ResponseEntity.ok(cardService.updateCard(id, json));
    }

    // delete a card
    @DeleteMapping("/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.deleteCard(id));
    }

}
