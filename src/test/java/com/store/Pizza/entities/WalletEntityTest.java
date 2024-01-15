package com.store.Pizza.entities;

import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class WalletEntityTest {

    @Test
    public void shouldAddCustomer_whenUserNameIsNotBlank() {
        //Arrange
        Customer customer = Customer.builder()
                .userName("Gabriela")
                .userAddress("Rua 1")
                .id(1L)
                .build();

        Wallet wallet = Wallet.builder()
                .id(1L)
                .balance(100.00)
                .build();

        //Act
        wallet.addCustomer(customer);

        //Assert
        assertEquals(customer, wallet.getCustomer());
        assertEquals(wallet, customer.getWallet());
    }

    @Test
    public void shouldAddCustomer_whenUserNameIsBlank() {
        //Arrange
        Customer customer = Customer.builder()
                .userName("")
                .userAddress("Rua 1")
                .id(1L)
                .build();

        Wallet wallet = Wallet.builder()
                .id(1L)
                .balance(100.00)
                .build();

        String expectedError = "O nome não pode estar em branco";

        //Act
        IllegalArgumentException actualError = assertThrows(IllegalArgumentException.class, () -> wallet.addCustomer(customer));

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }
}
