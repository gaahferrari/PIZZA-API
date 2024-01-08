package com.store.Pizza.controller;

import com.store.Pizza.DTO.PizzaIngredientsDTO;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.request.PizzaRequest;
import com.store.Pizza.responses.BaseBodyError;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pizza")
@Validated
public class PizzaController {
    private final PizzaService pizzaService;
    @Operation(summary = "Get All Pizza", description = "Get All Pizza", tags = {"Pizza"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<PizzaIngredientsDTO>>> getAllPizzas(){
        return ResponseEntity.status(200).body(pizzaService.getAll());
    }

    @Operation(summary = "Create Pizza", description = "Create Pizza", tags = {"Pizza"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @PostMapping
    public ResponseEntity<BaseBodyResponse<Pizza>> createPizza(@RequestBody @Valid PizzaRequest request){
        return ResponseEntity.status(201).body(pizzaService.create(request));
    }
    @Operation(summary = "Update Pizza", description = "Update Pizza", tags = {"Pizza"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})

    @PutMapping("/{pizzaId}/ingredients/{ingredientId}")
    public ResponseEntity<BaseBodyResponse<PizzaIngredientsDTO>> addPizzaToOrder(@PathVariable Long pizzaId, @PathVariable Long ingredientId) {
        return ResponseEntity.status(201).body(pizzaService.addIngredientsToPizza(pizzaId, ingredientId));
    }
    @Operation(summary = "Delete Pizza", description = "Delete Pizza", tags = {"Pizza"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.status(200).body("O cliente com o ID: " + id + " foi excluído com sucesso!");
    }
}
