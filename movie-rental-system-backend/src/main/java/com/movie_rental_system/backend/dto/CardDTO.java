package com.movie_rental_system.backend.dto;

import com.movie_rental_system.backend.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CardDTO {
    private Long card_id;
    private String customer_name;
    private String card_number;
    private String exp_date;
    private String cvv;
    private String holder_name;

    public CardDTO(Card card) {
        this.card_id = card.getCard_id();
        this.customer_name = card.getCustomer().getUser_name();
        this.card_number = card.getCard_number();
        this.exp_date = card.getExp_date();
        this.cvv = card.getCvv();
        this.holder_name = card.getHolder_name();
    }

    public static List<CardDTO> toCardDTOList(List<Card> cards) {
        return cards.stream().map(CardDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
