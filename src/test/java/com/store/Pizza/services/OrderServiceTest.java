package com.store.Pizza.services;

import com.store.Pizza.entity.*;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.exceptions.NotFoundException;
import com.store.Pizza.mapper.OrderMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.OrderRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
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

    @Test
    public void shouldAddPizzaToOrder_whenRequestIsValid() {
        try (MockedStatic<OrderMapper> orderMapper = mockStatic(OrderMapper.class)) {
            //Arrange
            Pizza pizza = mock(Pizza.class);

            Order order = mock(Order.class);

            when(pizzaRepository.findById(anyLong())).thenReturn(Optional.of(pizza));
            when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
            orderMapper.when(() -> OrderMapper.toOrder(any(OrderRequest.class))).thenReturn(order);

            //Act
            orderService.addPizzaToOrder(order.getId(), pizza.getId());

            //Assert
            verify(orderRepository).save(order);
            verify(order).addPizza(pizza);
        }
    }

    @Test
    public void shouldAddIngredientToPizza_whenRequestIsNotValid(){

        //Arrange

        Order order = mock(Order.class);

        Pizza pizza = mock(Pizza.class);

        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(pizzaRepository.findById(anyLong())).thenReturn(Optional.empty());

        String expectedError = "Pizza ou pedido não foi encontrado";
        //Act
        NotFoundException actualError = assertThrows(NotFoundException.class, () -> orderService.addPizzaToOrder(order.getId(), pizza.getId()));

        //Assert
        assertEquals(expectedError, actualError.getMessage());


    }

    @Test
    public void shouldDeleteOrder_whenRequestIsValid(){

        //Arrange
        Order order = mock(Order.class);

        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        //Act
        orderService.deleteOrder(order.getId());


        //Assert
        verify(order).removeOrder();
        verify(orderRepository).delete(order);

    }

    @Test
    public void shouldDeleteOrder_whenRequestIsNotValid(){

        //Arrange
        Order order = mock(Order.class);


        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        String expectedError = "Erro ao deletar pedido";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> orderService.deleteOrder(order.getId()));

        //Assert
        assertEquals(expectedError, actualError.getMessage());

    }

    @Test
    public void shouldReturnAllOrders_whenListIsNotEmpty() {
        try (MockedStatic<OrderMapper> orderMapper = mockStatic(OrderMapper.class)) {
            // Arrange
            Order order = mock(Order.class);
            List<Order> orders = Collections.singletonList(order);


            when(orderRepository.findAll()).thenReturn(orders);
            orderMapper.when(() -> OrderMapper.toOrder(any(OrderRequest.class))).thenReturn(order);

            // Act
            orderService.getAll();

            // Assert
            // verify(customerRepository, times(1)).findAll();
            verify(orderRepository).findAll();
        }
    }

    @Test
    public void shouldReturnAllOrders_whenListIsEmpty() {

        //Arrange
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());
        String expectedError = "A lista de pedidos está vazia";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> orderService.getAll());

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }

}


