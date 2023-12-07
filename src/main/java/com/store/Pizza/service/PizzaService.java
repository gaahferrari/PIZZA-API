package com.store.Pizza.service;


import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.entity.Order;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.mapper.OrderMapper;
import com.store.Pizza.mapper.PizzaMapper;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.request.PizzaRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    private final IngredientsRepository ingredientsRepository;

    public List<PizzaIngredientsDTO> getAll(){
        return pizzaRepository.findAll().stream().map(p -> PizzaMapper.toIngredientsDTO(p)).collect(Collectors.toList());
    }

    public Pizza create(PizzaRequest request) {
        return pizzaRepository.save(PizzaMapper.toPizza(request));
    }

    @Transactional
    public PizzaIngredientsDTO addIngredientsToPizza(Long pizzaId, Long ingredientId) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);
        Optional<Ingredients> IngredientsOptional = ingredientsRepository.findById(ingredientId);

        if (pizzaOptional.isPresent() && IngredientsOptional.isPresent()) {
            Pizza pizza = pizzaOptional.get();
            Ingredients ingredients = IngredientsOptional.get();
            pizza.addIngredient(ingredients);
            return PizzaMapper.toIngredientsDTO(pizzaRepository.save(pizza));
        } else {
            return null;
        }
    }
}
