package com.store.Pizza.entities;

import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.entity.Pizza;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderEntityTest {
    @Test
    public void shouldAddCustomer_whenUserNameIsNotBlank() {
        //Arrange
        Customer customer = Customer.builder()
                .userName("Gabriela")
                .userAddress("Rua 1")
                .id(1L)
                .orders(new ArrayList<>())
                .build();

        Order orders = Order.builder()
                .id(1L)
                .orderTotalPrice(10.00)
                .build();

        //Act
        orders.addCustomer(customer);

        //Assert
        assertEquals(customer, orders.getCustomer());
        assertEquals(orders, customer.getOrders().iterator().next());
        assertTrue(customer.getOrders().contains(orders));
        assertEquals("Gabriela", customer.getUserName());
    }

    @Test
    public void shouldAddPizza_whenNameIsBlank() {
        //Arrange
        Customer customer = Customer.builder()
                .userName("")
                .userAddress("Rua 1")
                .id(1L)
                .build();

        Order orders = Order.builder()
                .id(1L)
                .orderTotalPrice(10.00)
                .build();


        String expectedError = "O nome não pode estar em branco";

        //Act
        IllegalArgumentException actualError = assertThrows(IllegalArgumentException.class, () -> orders.addCustomer(customer));

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }

    @Test
    public void shouldAddPizza_whenNameIsNotBlank() {
        //Arrange
        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("Mussarela")
                .price(10.00)
                .orders(new HashSet<>())
                .size("Grande")
                .build();

        Order orders = Order.builder()
                .id(1L)
                .orderTotalPrice(10.00)
                .pizza(new HashSet<>())
                .build();

        //Act
        orders.addPizza(pizza);

        //Assert
        assertEquals(pizza, orders.getPizza().iterator().next());
        assertTrue(pizza.getOrders().contains(orders));
        assertTrue(pizza.getOrders().contains(orders));
        assertEquals("Mussarela", pizza.getName());
    }

    @Test
    public void shouldAddPizza_whenUserNameIsBlank() {
        //Arrange
        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("")
                .price(10.00)
                .orders(new HashSet<>())
                .size("Grande")
                .build();

        Order orders = Order.builder()
                .id(1L)
                .orderTotalPrice(10.00)
                .build();


        String expectedError = "O nome não pode estar em branco";

        //Act
        IllegalArgumentException actualError = assertThrows(IllegalArgumentException.class, () -> orders.addPizza(pizza));

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }

    @Test
    public void shouldRemoveOrder_WhenUserAndPizzaNameIsNotBlank() {
        //Arrange
        Customer customer = Customer.builder()
                .userName("Gabriela")
                .userAddress("Rua 1")
                .id(1L)
                .orders(new ArrayList<>())
                .build();

        Order order = Order.builder()
                .id(1L)
                .orderTotalPrice(10.00)
                .pizza(new HashSet<>())
                .customer(customer)
                .build();

        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("Mussarela")
                .price(10.00)
                .size("Grande")
                .orders(new HashSet<>())
                .build();

        order.addPizza(pizza);
        order.addCustomer(customer);

        //Act
        order.removeOrder();

        //Assert
        assertNull(order.getCustomer());
        assertFalse(customer.getOrders().contains(order));
        assertEquals(0, pizza.getOrders().size());
    }
    @Test
    public void shouldRemoveOrder_WhenUserAndPizzaNameIsBlank() {
        //Arrange
        Customer customer = Customer.builder()
                .userName("")
                .userAddress("Rua 1")
                .id(1L)
                .orders(new ArrayList<>())
                .build();

        Order order = Order.builder()
                .id(1L)
                .orderTotalPrice(10.00)
                .pizza(new HashSet<>())
                .customer(customer)
                .build();


        String expectedError = "O nome do cliente e da pizza não pode estar em branco";

        //Act
        IllegalArgumentException actualError = assertThrows(IllegalArgumentException.class, () -> order.removeOrder());

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }

}
