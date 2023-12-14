package com.store.Pizza.controller;


import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.IngredientsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<Ingredients>>> getAllOrders() {
            return ResponseEntity.status(200).body(ingredientsService.getAll());
    }

    @PostMapping
    public ResponseEntity<BaseBodyResponse<Ingredients>> createIngredients(@RequestBody IngredientsRequest request) {
        return ResponseEntity.status(201).body(ingredientsService.create(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ingredientsService.deleteIngredient(id);
        return ResponseEntity.status(200).body("O ingrediente foi excluído com sucesso!");
    }

}
