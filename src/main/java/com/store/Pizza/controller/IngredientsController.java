package com.store.Pizza.controller;


import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.responses.BaseBodyError;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.IngredientsService;
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
@RequestMapping("/ingredients")
@Validated
public class IngredientsController {

    private final IngredientsService ingredientsService;
    @Operation(summary = "Get All Ingredients", description = "Get All Ingredients", tags = {"Ingredients"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<Ingredients>>> getAllOrders() {
            return ResponseEntity.status(200).body(ingredientsService.getAll());
    }
    @Operation(summary = "Create Ingredients", description = "Create Ingredients", tags = {"Ingredients"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @PostMapping
    public ResponseEntity<BaseBodyResponse<Ingredients>> createIngredients(@RequestBody @Valid IngredientsRequest request) {
        return ResponseEntity.status(201).body(ingredientsService.create(request));
    }
    @Operation(summary = "Delete Ingredients", description = "Delete Ingredients", tags = {"Ingredients"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ingredientsService.deleteIngredient(id);
        return ResponseEntity.status(200).body("O ingrediente foi excluído com sucesso!");
    }

}
