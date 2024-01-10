package com.store.Pizza.service;


import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.exceptions.NotFoundException;
import com.store.Pizza.mapper.PizzaMapper;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.PizzaRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    private final IngredientsRepository ingredientsRepository;

    public BaseBodyResponse<List<PizzaIngredientsDTO>> getAll(){
        List<Pizza> pizzaList = pizzaRepository.findAll();
        if(pizzaList.isEmpty()){
            throw new BadRequestException("A lista de Pizza está vazia");
        }
        return PizzaMapper.toListResponse(pizzaList);
    }

    public BaseBodyResponse<Pizza> create(@Valid PizzaRequest request) {
        Pizza pizza = pizzaRepository.save(PizzaMapper.toPizza(request));
        if(pizza != null){
            return PizzaMapper.toResponse(pizza);
        } else {
            throw new BadRequestException("Erro ao criar uma nova pizza");
        }

    }

    @Transactional
    public BaseBodyResponse<PizzaIngredientsDTO> addIngredientsToPizza(Long pizzaId, Long ingredientId) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);
        Optional<Ingredients> IngredientsOptional = ingredientsRepository.findById(ingredientId);

        if(pizzaOptional.isEmpty() || IngredientsOptional.isEmpty()){
            throw new NotFoundException("Pizza ou ingrediente não foi encontrado");
        }
            Pizza pizza = pizzaOptional.get();
            Ingredients ingredients = IngredientsOptional.get();
            pizza.addIngredient(ingredients);

            Pizza pizzas = pizzaRepository.save(pizza);
            return PizzaMapper.toResponseIngredients(pizzas);
    }

    @Transactional
    public void deletePizza(Long pizzaId) {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        if (pizza != null) {
            pizza.removePizza();
            pizzaRepository.delete(pizza);
        } else {
            throw new BadRequestException("Erro ao deletar sua pizza");
        }

    }
}
