package com.movie_rental_system.backend.controller;

import com.movie_rental_system.backend.dto.CardDTO;
import com.movie_rental_system.backend.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<CardDTO>> getAllCards() {
        return ResponseEntity.ok(CardDTO.toCardDTOList(cardService.getAllCards()));
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
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(new CardDTO(cardService.createCard(cardDTO)));
    }

    // update a card
    // example request body: same as create card
    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long id, @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(new CardDTO(cardService.updateCard(id, cardDTO)));
    }

    // delete a card
    @DeleteMapping("/{id}")
    public ResponseEntity<CardDTO> deleteCard(@PathVariable Long id) {
        return ResponseEntity.ok(new CardDTO(cardService.deleteCard(id)));
    }

}
