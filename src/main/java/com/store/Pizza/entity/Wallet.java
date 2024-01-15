package com.store.Pizza.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public void addCustomer(Customer customer){
        if(customer.getUserName().isBlank()){
            throw new IllegalArgumentException("O nome não pode estar em branco");
        }
        setCustomer(customer);
        customer.setWallet(this);
    }


}
