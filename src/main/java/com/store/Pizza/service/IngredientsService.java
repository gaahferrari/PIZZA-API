package com.store.Pizza.service;

import com.store.Pizza.DTO.IngredientsDTO;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.mapper.IngredientsMapper;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.request.IngredientsRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public List<Ingredients> getAll(){
        return ingredientsRepository.findAll();
    }

    public Ingredients create(IngredientsRequest request) {

        return ingredientsRepository.save(IngredientsMapper.toIngredients(request));
    }
}
