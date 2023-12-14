package com.store.Pizza.mapper;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.CustomerOrdersDTO;
import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.responses.BaseBodyResponse;

import java.util.List;


public class CustomerMapper {

    public static Customer toCustomer (CustomerRequest customer){
        return Customer.builder()
                .userName(customer.getUserName())
                .userAddress(customer.getUserAddress())
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

    public static BaseBodyResponse<Customer> toResponse(Customer customer){
        return BaseBodyResponse.<Customer>builder()
                .company("Pizza Store")
                .description("Usuário " + customer.getUserName() + " foi criado com sucesso!" )
                .result(customer).build();
    }

    public static BaseBodyResponse<List<CustomerDTO>> toListResponse(List<Customer> customers){
        List<CustomerDTO> customerDTOS = customers.stream().map(CustomerMapper::toDTO).toList();
        return BaseBodyResponse.<List<CustomerDTO>>builder()
                .company("Pizza Store")
                .description("Lista de usuários")
                .result(customerDTOS)
                .build();
    }

    public static BaseBodyResponse<CustomerOrdersDTO> toResponseID(Customer customer, List<Order> orders){
        return BaseBodyResponse.<CustomerOrdersDTO>builder()
                .company("Pizza Store")
                .description("Pedidos do usuário: " + customer.getUserName())
                .result(toOrdersDTO(customer, orders)).build();
    }


}
