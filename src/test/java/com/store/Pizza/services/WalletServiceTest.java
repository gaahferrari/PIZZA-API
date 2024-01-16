package com.store.Pizza.services;

import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.mapper.WalletMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.WalletRepository;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.service.WalletService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {
    @Mock
    WalletRepository walletRepository;
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    @Spy
    WalletService walletService;

    @Test
    public void shouldCreateWallet_whenRequestIsValid(){

        try(MockedStatic<WalletMapper> walletMapper = mockStatic(WalletMapper.class)) {
            //Arrange
            Customer customer = mock(Customer.class);

            WalletRequest walletRequest = mock(WalletRequest.class);

            Wallet wallet = mock(Wallet.class);

            when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
            when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);
            walletMapper.when(() -> WalletMapper.toWallet(any(WalletRequest.class))).thenReturn(wallet);

            //Act
            walletService.create(walletRequest);

            //Assert
            verify(walletRepository).save(wallet);
            verify(wallet).addCustomer(customer);
        }
    }

    @Test
    public void shouldCreateWallet_whenRequestIsNotValid(){

            //Arrange

            WalletRequest walletRequest = mock(WalletRequest.class);


            when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

            String expectedError = "Usuário com o id " + walletRequest.getCustomerId() + " não existe";
            //Act
            BadRequestException actualError = assertThrows(BadRequestException.class, () -> walletService.create(walletRequest));

            //Assert
            assertEquals(expectedError, actualError.getMessage());

    }

    @Test
    public void shouldReturnAllWallets_whenListIsNotEmpty() {
        try (MockedStatic<WalletMapper> walletMapper = mockStatic(WalletMapper.class)) {
            // Arrange
            Wallet wallet = mock(Wallet.class);
            List<Wallet> wallets = Collections.singletonList(wallet);


            when(walletRepository.findAll()).thenReturn(wallets);
            walletMapper.when(() -> WalletMapper.toWallet(any(WalletRequest.class))).thenReturn(wallet);

            // Act
            walletService.getAll();

            // Assert
            // verify(customerRepository, times(1)).findAll();
            verify(walletRepository).findAll();
        }
    }

    @Test
    public void shouldReturnAllWallets_whenListIsEmpty() {

        //Arrange
        when(walletRepository.findAll()).thenReturn(Collections.emptyList());
        String expectedError = "A lista de carteiras está vazia";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> walletService.getAll());
        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }



}
