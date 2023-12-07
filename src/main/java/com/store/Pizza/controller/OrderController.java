package com.store.Pizza.controller;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.entity.Order;
import com.store.Pizza.request.OrderRequest;
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
    public List<OrderPizzaDTO> getAllOrders(){
        return orderService.getAll();
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderRequest request){
        return orderService.create(request);
    }
  //  @DeleteMapping("/{id}")
    //public ResponseEntity<String> delete(@PathVariable Long id) {
      //  orderService.deleteOrderWithoutRemovingAssociatedEntities(id);
        //return ResponseEntity.ok("O pedido com o ID: " + id + " foi excluído com sucesso!");
    //}


    @PutMapping("/{orderId}/pizza/{pizzaId}")
    public OrderPizzaDTO addPizzaToOrder(@PathVariable Long orderId, @PathVariable Long pizzaId) {
        return orderService.addPizzaToOrder(orderId,pizzaId);
    }

}
