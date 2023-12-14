package com.store.Pizza.service;

import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.mapper.IngredientsMapper;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public BaseBodyResponse<List<Ingredients>> getAll(){
        List<Ingredients> ingredients = ingredientsRepository.findAll();
        if(ingredients.isEmpty()){
            throw new BadRequestException("A lista de ingredientes está vazia");
        }
        return IngredientsMapper.toListResponse(ingredients);
    }

    public BaseBodyResponse<Ingredients> create(IngredientsRequest request) {
      Ingredients ingredients = ingredientsRepository.save(IngredientsMapper.toIngredients(request));
        if (ingredients != null){
            return IngredientsMapper.toResponse(ingredients);
        } else {
            throw new BadRequestException("Erro ao criar um novo ingrediente");
        }
    }
}
