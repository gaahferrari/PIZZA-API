package com.store.Pizza.controller;

import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.request.PizzaRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pizza")
public class PizzaController {
    private final PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<PizzaIngredientsDTO>>> getAllPizzas(){
        return ResponseEntity.status(200).body(pizzaService.getAll());
    }

    @PostMapping
    public ResponseEntity<BaseBodyResponse<Pizza>> createPizza(@RequestBody PizzaRequest request){
        return ResponseEntity.status(201).body(pizzaService.create(request));
    }

    @PutMapping("/{pizzaId}/ingredients/{ingredientId}")
    public ResponseEntity<BaseBodyResponse<PizzaIngredientsDTO>> addPizzaToOrder(@PathVariable Long pizzaId, @PathVariable Long ingredientId) {
        return ResponseEntity.status(201).body(pizzaService.addIngredientsToPizza(pizzaId, ingredientId));
    }
}
