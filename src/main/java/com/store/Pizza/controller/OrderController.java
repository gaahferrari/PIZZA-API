package com.store.Pizza.controller;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<OrderPizzaDTO>>> getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAll());
    }

    @PostMapping
    public ResponseEntity<BaseBodyResponse<OrderDTO>> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.status(201).body(orderService.create(request));
    }


  //  @DeleteMapping("/{id}")
    //public ResponseEntity<String> delete(@PathVariable Long id) {
      //  orderService.deleteOrderWithoutRemovingAssociatedEntities(id);
        //return ResponseEntity.ok("O pedido com o ID: " + id + " foi excluído com sucesso!");
    //}


    @PutMapping("/{orderId}/pizza/{pizzaId}")
    public ResponseEntity<BaseBodyResponse<OrderPizzaDTO>> addPizzaToOrder(@PathVariable Long orderId, @PathVariable Long pizzaId) {
        return ResponseEntity.status(200).body(orderService.addPizzaToOrder(orderId,pizzaId));
    }

}
