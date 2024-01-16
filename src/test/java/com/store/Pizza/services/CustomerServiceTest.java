package com.store.Pizza.services;

import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.exceptions.NotFoundException;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    @Spy
    CustomerService customerService;

    @Test
    public void shouldCreateCustomer_whenRequestIsValid() {
        try (MockedStatic<CustomerMapper> customerMapper = mockStatic(CustomerMapper.class)) {
            //Arrange
            Customer customer = mock(Customer.class);

            CustomerRequest customerRequest = mock(CustomerRequest.class);

            when(customerRepository.save(any(Customer.class))).thenReturn(customer);
            customerMapper.when(() -> CustomerMapper.toCustomer(any(CustomerRequest.class))).thenReturn(customer);

            //Act
            customerService.create(customerRequest);

            //Assert
            verify(customerRepository).save(customer);
        }
    }

    @Test
    public void shouldCreateCustomer_whenRequestIsNotValid(){

        //Arrange
        CustomerRequest customerRequest = mock(CustomerRequest.class);

        when(customerRepository.save(any(Customer.class))).thenReturn(null);

        String expectedError = "Erro ao cadastrar o usuário";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> customerService.create(customerRequest));

        //Assert
        assertEquals(expectedError, actualError.getMessage());

    }
    @Test
    public void shouldDeleteCustomer_whenRequestIsValid(){

        //Arrange
        Customer customer = mock(Customer.class);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //Act
        customerService.deleteCustomerAndWallet(customer.getId());

        //Assert
        verify(customerRepository).delete(customer);

    }

    @Test
    public void shouldDeleteCustomer_whenRequestIsNotValid(){

        //Arrange
        Customer customer = mock(Customer.class);

        when(customerRepository.findById(any())).thenReturn(Optional.empty());
        String expectedError = "Erro ao deletar o usuário";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> customerService.deleteCustomerAndWallet(customer.getId()));

        //Assert
        assertEquals(expectedError, actualError.getMessage());

    }

    @Test
    public void shouldReturnAllCustomers_whenListIsNotEmpty() {
        try (MockedStatic<CustomerMapper> customerMapper = mockStatic(CustomerMapper.class)) {
            // Arrange
            Customer customer = mock(Customer.class);
            List<Customer> customers = Collections.singletonList(customer);


            when(customerRepository.findAll()).thenReturn(customers);
            customerMapper.when(() -> CustomerMapper.toCustomer(any(CustomerRequest.class))).thenReturn(customer);

            // Act
            customerService.getAll();

            // Assert
           // verify(customerRepository, times(1)).findAll();
            verify(customerRepository).findAll();
        }
    }

    @Test
    public void shouldReturnAllCustomers_whenListIsEmpty() {

        //Arrange
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());
        String expectedError = "A lista de usuários está vazia";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> customerService.getAll());

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }

    @Test
    public void shouldFindCustomer_whenRequestIsValid(){
        try (MockedStatic<CustomerMapper> customerMapper = mockStatic(CustomerMapper.class)) {
            //Arrange
            Customer customer = mock(Customer.class);
            List<Order> orders = Collections.singletonList(mock(Order.class));
            customer.setOrders(orders);

            when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
            customerMapper.when(() -> CustomerMapper.toCustomer(any(CustomerRequest.class))).thenReturn(customer);

            //Act
            customerService.getByOrder(customer.getId());

            //Assert
            verify(customerRepository, times(1)).findById(customer.getId());
        }
    }

    @Test
    public void shouldFindCustomer_whenRequestIsNotValid(){

        //Arrange
        Customer customer = mock(Customer.class);

        when(customerRepository.findById(any())).thenReturn(Optional.empty());
        String expectedError = "Usuário com o id: " + customer.getId() + " não foi encontrado";
        //Act
        NotFoundException actualError = assertThrows(NotFoundException.class, () -> customerService.getByOrder(customer.getId()));

        //Assert
        assertEquals(expectedError, actualError.getMessage());

    }



}
