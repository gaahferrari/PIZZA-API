package com.store.Pizza.services;

import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.mapper.OrderMapper;
import com.store.Pizza.mapper.WalletMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.repository.OrderRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.service.OrderService;
import com.store.Pizza.service.PizzaService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    CustomerRepository customerRepository;
    @Mock
    PizzaRepository pizzaRepository;

    @InjectMocks
    @Spy
    OrderService orderService;


    @Test
    public void shouldCreateOrder_whenRequestIsValid(){

        try(MockedStatic<OrderMapper> orderMapper = mockStatic(OrderMapper.class)) {
            //Arrange
            Customer customer = mock(Customer.class);

            OrderRequest orderRequest = mock(OrderRequest.class);

           Order order = mock(Order.class);

            when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
            when(orderRepository.save(any(Order.class))).thenReturn(order);
            orderMapper.when(() -> OrderMapper.toOrder(any(OrderRequest.class))).thenReturn(order);

            //Act
            orderService.create(orderRequest);

            //Assert
            verify(orderRepository).save(order);
            verify(order).addCustomer(customer);
        }
    }

    @Test
    public void shouldCreateOrder_whenRequestIsNotValid(){

        //Arrange

        OrderRequest orderRequest = mock(OrderRequest.class);


        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        String expectedError = "O usuário com o id " + orderRequest.getCustomerId() + " não existe";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> orderService.create(orderRequest));

        //Assert
        assertEquals(expectedError, actualError.getMessage());

    }
}


