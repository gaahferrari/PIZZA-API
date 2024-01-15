package com.store.Pizza.services;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.mapper.WalletMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.responses.BaseBodyResponse;
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
import static org.mockito.ArgumentMatchers.anyLong;
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



}
