package com.store.Pizza.mapper;

import com.store.Pizza.DTO.IngredientsDTO;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.responses.BaseBodyResponse;

import java.util.List;

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


    public static BaseBodyResponse<Ingredients> toResponse(Ingredients ingredients){
        return BaseBodyResponse.<Ingredients>builder()
                .company("Pizza Store")
                .description("Ingrediente " + ingredients.getName() + " foi criado com sucesso!")
                .result(ingredients).build();
    }

    public static BaseBodyResponse<List<Ingredients>> toListResponse(List<Ingredients> Ingredients){
        return BaseBodyResponse.<List<Ingredients>>builder()
                .company("Pizza Store")
                .description("Lista de ingredientes")
                .result(Ingredients)
                .build();
    }
}
