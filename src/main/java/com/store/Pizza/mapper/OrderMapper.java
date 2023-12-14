package com.store.Pizza.mapper;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.entity.Order;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.responses.BaseBodyResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toOrder(OrderRequest request){
        return Order.builder()
                .orderTotalPrice(request.getOrderTotalPrice())
                .build();
    }

    public static OrderDTO toDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .orderTotalPrice(order.getOrderTotalPrice())
                .customerId(order.getCustomer().getId())
                .name(order.getCustomer().getUserName())
                .address(order.getCustomer().getUserAddress())
                .build();
    }

    public static OrderPizzaDTO toPizzaDTO(Order order){

        return OrderPizzaDTO.builder()
                .id(order.getId())
                .orderTotalPrice(order.getOrderTotalPrice())
                .customerId(order.getCustomer().getId())
                .name(order.getCustomer().getUserName())
                .address(order.getCustomer().getUserAddress())
                .pizzaIds(order.getPizza().stream().map(PizzaMapper::toIngredientsDTO).collect(Collectors.toSet()))
                .build();
    }


    public static BaseBodyResponse<OrderDTO> toResponse(Order order){
        return BaseBodyResponse.<OrderDTO>builder()
                .company("Pizza Store")
                .description("Pedido criado com sucesso!")
                .result(toDTO(order)).build();
    }

    public static BaseBodyResponse<OrderPizzaDTO> toResponsePizza(Order order){
        return BaseBodyResponse.<OrderPizzaDTO>builder()
                .company("Pizza Store")
                .description("Uma nova pizza foi adicionada ao pedido")
                .result(toPizzaDTO(order)).build();
    }

    public static BaseBodyResponse<List<OrderPizzaDTO>> toListResponse(List<Order> orders){
        List<OrderPizzaDTO> orderPizzaDTOS = orders.stream().map(OrderMapper::toPizzaDTO).toList();
        return BaseBodyResponse.<List<OrderPizzaDTO>>builder()
                .company("Pizza Store")
                .description("Lista de pedidos")
                .result(orderPizzaDTOS)
                .build();
    }



}
