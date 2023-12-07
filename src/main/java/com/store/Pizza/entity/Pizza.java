package com.store.Pizza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String size;

    private Double price;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
           name = "pizza_orders",
            joinColumns = @JoinColumn(name = "pizza_fk"),
            inverseJoinColumns = @JoinColumn(name = "orders_fk")
    )
    private Set<Order> orders = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pizza_ingredients",
            joinColumns = @JoinColumn(name = "pizza_fk"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_fk")
    )
    private Set<Ingredients> ingredients = new HashSet<>();


    public void addIngredient(Ingredients ingredient){
        ingredients.add(ingredient);
        ingredient.getPizza().add(this);
    }

}
