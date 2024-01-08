package com.store.Pizza.controller;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.responses.BaseBodyError;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.OrderService;
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
@RequestMapping("/orders")
@Validated
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Get All Orders", description = "Get All Orders", tags = {"Orders"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<OrderPizzaDTO>>> getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAll());
    }

    @Operation(summary = "Create Orders", description = "Create Orders", tags = {"Orders"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @PostMapping
    public ResponseEntity<BaseBodyResponse<OrderDTO>> createOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.status(201).body(orderService.create(request));
    }

    @Operation(summary = "Delete Orders", description = "Delete Orders", tags = {"Orders"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
   @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
       return ResponseEntity.status(200).body("O pedido com o ID: " + id + " foi excluído com sucesso!");
    }

    @Operation(summary = "Update Orders", description = "Update Orders", tags = {"Orders"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @PutMapping("/{orderId}/pizza/{pizzaId}")
    public ResponseEntity<BaseBodyResponse<OrderPizzaDTO>> addPizzaToOrder(@PathVariable Long orderId, @PathVariable Long pizzaId) {
        return ResponseEntity.status(200).body(orderService.addPizzaToOrder(orderId,pizzaId));
    }

}
