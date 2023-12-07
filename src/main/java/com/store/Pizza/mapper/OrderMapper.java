package com.store.Pizza.mapper;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.DTO.PizzaDTO;
import com.store.Pizza.entity.Order;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.request.OrderRequest;

import java.util.Set;
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






}
