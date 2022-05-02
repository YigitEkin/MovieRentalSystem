package com.movie_rental_system.backend.repository;

import com.movie_rental_system.backend.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    // find cards of customer
    @Query("select c from Card c where c.customer.user_name = ?1")
    List<Card> findCardsOfCustomer(String customerName);

    // get card of customer
    @Query("select c from Card c where c.customer.user_name = ?1 and c.card_id = ?2")
    Card getCardOfCustomer(String customerName, Long cardId);
}
