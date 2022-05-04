package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Card;
import com.movie_rental_system.backend.exception.CardNotFoundException;
import com.movie_rental_system.backend.exception.CustomerNotFoundException;
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
        Card card = cardRepository.findById(id).orElse(null);
        if (card == null) {
            throw new CardNotFoundException("Card with id " + id + " not found");
        }
        return card;
    }

    // get card by customer id
    public List<Card> getCardsOfCustomer(String customerName) {
        if (!customerRepository.existsById(customerName))
            throw new CustomerNotFoundException("Customer with name " + customerName + " not found");
        return cardRepository.findCardsOfCustomer(customerName);
    }

    // get card of customer by id
    public Card getCardOfCustomer(String customerName, Long cardId) {
        if(!customerRepository.existsById(customerName))
            throw new CustomerNotFoundException("Customer with name " + customerName + " not found");
        else if(!cardRepository.existsById(cardId))
            throw new CardNotFoundException("Card with id " + cardId + " not found");
        Card card = cardRepository.getCardOfCustomer(customerName, cardId);
        if(card == null)
            throw new CardNotFoundException("Card with id " + cardId + " doesnt belong to customer " + customerName);

        return card;
    }

    // create card
    public Card createCard(Map<String, String> json) {
        if(customerRepository.findById(json.get("customer_name")).orElse(null) == null)
            throw new CustomerNotFoundException("Customer with name " + json.get("customer_name") + " not found");
        return cardRepository.save(new Card(0L,
                customerRepository.findById(json.get("customer_name")).orElse(null),
                json.get("card_number"), json.get("exp_date"), json.get("cvv"), json.get("holder_name")));
    }

    // update card
    public Card updateCard(Long card_id, Map<String, String> json) {
        Card temp = cardRepository.findById(card_id).orElse(null);
        if (temp == null)
            throw new CardNotFoundException("Card with id " + card_id + " not found");

        temp.setCustomer(customerRepository.findById(json.get("customer_name")).orElse(null));
        if(temp.getCustomer() == null)
            throw new CustomerNotFoundException("Customer with name " + json.get("customer_name") + " not found");

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
        if (temp == null)
            throw new CardNotFoundException("Card with id " + id + " not found");
        cardRepository.deleteById(id);
        return temp;
    }
}
