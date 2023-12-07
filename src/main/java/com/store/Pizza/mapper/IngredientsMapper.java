package com.store.Pizza.mapper;

import com.store.Pizza.DTO.IngredientsDTO;
import com.store.Pizza.DTO.PizzaDTO;
import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.request.PizzaRequest;

import java.util.stream.Collectors;

public class IngredientsMapper {

    public static Ingredients toIngredients(IngredientsRequest request){
        return Ingredients.builder()
                .name(request.getName())
                .build();
    }

    public static IngredientsDTO toDTO(Ingredients ingredients){
        return IngredientsDTO.builder()
                .name(ingredients.getName())
                .id(ingredients.getId())
                .build();
    }


}
