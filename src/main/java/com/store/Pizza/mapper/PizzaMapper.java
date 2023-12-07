package com.store.Pizza.mapper;


import com.store.Pizza.DTO.PizzaDTO;
import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.request.PizzaRequest;

import java.util.stream.Collectors;


public class PizzaMapper {

    public static Pizza toPizza(PizzaRequest request){
        return Pizza.builder()
                .name(request.getName())
                .price(request.getPrice())
                .size(request.getSize())
                .build();
    }

    public static PizzaDTO toDTO(Pizza pizza){
        return PizzaDTO.builder()
                .name(pizza.getName())
                .price(pizza.getPrice())
                .size(pizza.getSize())
                .id(pizza.getId())
                .build();
    }

    public static PizzaIngredientsDTO toIngredientsDTO(Pizza pizza){
        return PizzaIngredientsDTO.builder()
                .name(pizza.getName())
                .price(pizza.getPrice())
                .size(pizza.getSize())
                .id(pizza.getId())
                .ingredientsIds(pizza.getIngredients().stream().map(IngredientsMapper::toDTO).collect(Collectors.toSet()))
                .build();
    }
}
