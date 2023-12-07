package com.store.Pizza.controller;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.request.PizzaRequest;
import com.store.Pizza.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pizza")
public class PizzaController {
    private final PizzaService pizzaService;

    @GetMapping
    public List<PizzaIngredientsDTO> getAllPizzas(){
        return pizzaService.getAll();
    }

    @PostMapping
    public Pizza createPizza(@RequestBody PizzaRequest request){
        return pizzaService.create(request);
    }

    @PutMapping("/{pizzaId}/ingredients/{ingredientId}")
    public PizzaIngredientsDTO addPizzaToOrder(@PathVariable Long pizzaId, @PathVariable Long ingredientId) {
        return pizzaService.addIngredientsToPizza(pizzaId, ingredientId);
    }
}
