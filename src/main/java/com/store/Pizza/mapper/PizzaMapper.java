package com.store.Pizza.mapper;


import com.store.Pizza.DTO.PizzaDTO;
import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.request.PizzaRequest;
import com.store.Pizza.responses.BaseBodyResponse;

import java.util.List;
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

    public static BaseBodyResponse<Pizza> toResponse(Pizza pizza){
        return BaseBodyResponse.<Pizza>builder()
                .company("Pizza Store")
                .description("Pizza criada com sucesso!")
                .result(pizza).build();
    }

    public static BaseBodyResponse<PizzaIngredientsDTO> toResponseIngredients(Pizza pizza){
        return BaseBodyResponse.<PizzaIngredientsDTO>builder()
                .company("Pizza Store")
                .description("Um novo ingrediente foi adicionado a pizza sabor: " + pizza.getName())
                .result(toIngredientsDTO(pizza)).build();
    }

    public static BaseBodyResponse<List<PizzaIngredientsDTO>> toListResponse(List<Pizza> pizzas){
        List<PizzaIngredientsDTO> pizzaIngredientsDTOS = pizzas.stream().map(PizzaMapper::toIngredientsDTO).toList();
        return BaseBodyResponse.<List<PizzaIngredientsDTO>>builder()
                .company("Pizza Store")
                .description("Lista de Pizzas")
                .result(pizzaIngredientsDTOS)
                .build();
    }
}
