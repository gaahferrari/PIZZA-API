package com.store.Pizza.controller;

import com.store.Pizza.DTO.IngredientsDTO;
import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.service.IngredientsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    @GetMapping
    public List<Ingredients> getAllOrders(){
        return ingredientsService.getAll();
    }

    @PostMapping
    public Ingredients createIngredients(@RequestBody IngredientsRequest request){
        return ingredientsService.create(request);
    }


}
