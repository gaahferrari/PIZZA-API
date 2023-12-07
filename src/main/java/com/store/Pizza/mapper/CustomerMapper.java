package com.store.Pizza.mapper;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.CustomerOrdersDTO;
import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.request.CustomerRequest;

import java.util.List;


public class CustomerMapper {

    public static Customer toCustomer (CustomerRequest customer){
        return Customer.builder()
                .userName(customer.getUserName())
                .userAddress(customer.getUserAdress())
                .build();
    }

    public static CustomerDTO toDTO (Customer customer){


        return CustomerDTO.builder()
                .userName(customer.getUserName())
                .userAddress(customer.getUserAddress())
                .id(customer.getId())
                .balance(customer.getWallet().getBalance())
                .build();
    }

    public static CustomerOrdersDTO toOrdersDTO(Customer customer, List<Order> orders){
        List<OrderDTO> ordersDTO = orders.stream().map(OrderMapper::toDTO).toList();

        return CustomerOrdersDTO.builder()
                .id(customer.getId())
                .balance(customer.getWallet().getBalance())
                .userName(customer.getUserName())
                .userAddress(customer.getUserAddress())
                .orders(ordersDTO)
                .build();
    }
}
