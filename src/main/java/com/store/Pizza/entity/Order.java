package com.store.Pizza.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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


    public void addCustomer(Customer customer) {
        if (customer.getUserName().isBlank()) {
            throw new IllegalArgumentException("O nome não pode estar em branco");
        } else {
            setCustomer(customer);
            customer.getOrders().add(this);
        }
    }

    public void addPizza(Pizza pizzas) {
        if (pizzas.getName().isBlank()) {
            throw new IllegalArgumentException("O nome não pode estar em branco");
        } else {
            pizza.add(pizzas);
            pizzas.getOrders().add(this);
        }
    }

    public void removeOrder() {

        if (this.customer.getUserName().isBlank() || this.pizza.isEmpty()) {
            throw new IllegalStateException("O nome do cliente e da pizza não pode estar em branco");

        } else {
            for (Pizza pizza : new HashSet<>(this.pizza))
                pizza.getOrders().remove(this);
            this.pizza.remove(pizza);
        }
        this.customer.getOrders().remove(this);
        this.customer = null;
    }
}




