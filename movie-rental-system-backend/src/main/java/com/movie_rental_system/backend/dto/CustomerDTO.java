package com.movie_rental_system.backend.dto;

import com.movie_rental_system.backend.entity.NewCustomer;
import com.movie_rental_system.backend.entity.RepeatCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String user_name;
    private String password;
    private String user_email;
    private String birth_year;
    private double balance;
    private String promotion_code;
    private String customer_rank;
    private String discount_code;

    public CustomerDTO(NewCustomer newCustomer) {
        this.user_name = newCustomer.getUser_name();
        this.password = newCustomer.getPassword();
        this.user_email = newCustomer.getUser_email();
        this.birth_year = newCustomer.getBirth_year().toString();
        this.balance = newCustomer.getBalance();
        this.promotion_code = newCustomer.getPromotion_code();
    }

    public CustomerDTO(RepeatCustomer repeatCustomer) {
        this.user_name = repeatCustomer.getUser_name();
        this.password = repeatCustomer.getPassword();
        this.user_email = repeatCustomer.getUser_email();
        this.birth_year = repeatCustomer.getBirth_year().toString();
        this.balance = repeatCustomer.getBalance();
        this.customer_rank = repeatCustomer.getCustomer_rank();
        this.discount_code = repeatCustomer.getDiscount_code();
    }

    public static List<CustomerDTO> toNewCustomerDTOList(List<NewCustomer> customers) {
        return customers.stream().map(CustomerDTO::new).collect(java.util.stream.Collectors.toList());
    }

    public static List<CustomerDTO> toRepeatCustomerDTOList(List<RepeatCustomer> customers) {
        return customers.stream().map(CustomerDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
