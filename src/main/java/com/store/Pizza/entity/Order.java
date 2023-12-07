package com.store.Pizza.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double orderTotalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @ManyToMany(mappedBy = "orders")
    private Set<Pizza> pizza = new HashSet<>();


    public void addCustomer(Customer customer){
        setCustomer(customer);
        customer.getOrders().add(this);
    }

    public void addPizza(Pizza pizzas){
        pizza.add(pizzas);
        pizzas.getOrders().add(this);
    }

   // public void removeAllPizzas() {
     //   this.pizza.clear();
    //}


}
