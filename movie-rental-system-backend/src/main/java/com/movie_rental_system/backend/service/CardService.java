package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Card;
import com.movie_rental_system.backend.repository.CardRepository;
import com.movie_rental_system.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CardService(CardRepository cardRepository, CustomerRepository customerRepository) {
        this.cardRepository = cardRepository;
        this.customerRepository = customerRepository;
    }

    // get all cards
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    // get card by id
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    // get card by customer id
    public List<Card> getCardsOfCustomer(String customerName) {
        return cardRepository.findCardsOfCustomer(customerName);
    }

    // get card of customer by id
    public Card getCardOfCustomer(String customerName, Long cardId) {
        return cardRepository.getCardOfCustomer(customerName, cardId);
    }

    // create card
    public Card createCard(Map<String, String> json) {
        if(customerRepository.findById(json.get("customer_name")).orElse(null) == null) {
            System.out.println("Customer not found error should be implemented");
            return null;
        }
        return cardRepository.save(new Card(0L,
                customerRepository.findById(json.get("customer_name")).orElse(null),
                json.get("card_number"), json.get("exp_date"), json.get("cvv"), json.get("holder_name")));
    }

    // update card
    public Card updateCard(Long card_id, Map<String, String> json) {
        Card temp = cardRepository.findById(card_id).orElse(null);
        if (temp == null) {
            System.out.println("Card not found error should be implemented");
            return null;
        }
        temp.setCustomer(customerRepository.findById(json.get("customer_name")).orElse(null));
        if(temp.getCustomer() == null){
            System.out.println("Customer not found error should be implemented");
            return null;
        }
        temp.setCard_number(json.get("card_number"));
        temp.setCvv(json.get("cvv"));
        temp.setExp_date(json.get("exp_date"));
        temp.setCard_number(json.get("card_number"));
        temp.setHolder_name(json.get("holder_name"));
        return cardRepository.save(temp);
    }

    // delete card
    public Card deleteCard(Long id) {
        Card temp = cardRepository.findById(id).orElse(null);
        cardRepository.deleteById(id);
        return temp;
    }
}
