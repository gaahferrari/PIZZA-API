package com.store.Pizza.entities;

import com.store.Pizza.entity.*;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PizzaEntityTest {

    @Test
    public void shouldAddIngredient_whenNameIsNotBlank() {
        //Arrange
        Ingredients ingredients = Ingredients.builder()
                .name("Tomate")
                .id(1L)
                .pizza(new HashSet<>())
                .build();

        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("Mussarela")
                .size("Grande")
                .price(20.00)
                .ingredients(new HashSet<>())
                .build();

        //Act
        pizza.addIngredient(ingredients);

        //Assert
        assertTrue(pizza.getIngredients().contains(ingredients));
        assertTrue(ingredients.getPizza().contains(pizza));
        assertEquals("Tomate", ingredients.getName());
    }


    @Test
    public void shouldAddIngredients_whenNameIsBlank() {
        Ingredients ingredients = Ingredients.builder()
                .name("")
                .id(1L)
                .build();

        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("Mussarela")
                .size("Grande")
                .price(20.00)
                .build();

        String expectedError = "O nome do ingrediente não pode estar em branco";

        //Act
        IllegalArgumentException actualError = assertThrows(IllegalArgumentException.class, () -> pizza.addIngredient(ingredients));

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }

    @Test
    public void shouldRemovePizza_WhenNameIsEmpty() {
        //Arrange
        Ingredients ingredients = Ingredients.builder()
                .name("Tomate")
                .id(1L)
                .pizza(new HashSet<>())
                .build();

        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("Mussarela")
                .size("Grande")
                .price(20.00)
                .ingredients(new HashSet<>())
                .build();


        pizza.addIngredient(ingredients);


        //Act
        pizza.removePizza();

        //Assert
        assertFalse(pizza.getIngredients().contains(ingredients));
        assertEquals(0, pizza.getIngredients().size());
        assertTrue(ingredients.getPizza().isEmpty());
    }

    @Test
    public void shouldRemovePizza_WhenIngredientNameIsBlank() {
        //Arrange
        Pizza pizza = Pizza.builder()
                .id(1L)
                .name("Mussarela")
                .size("Grande")
                .price(20.00)
                .ingredients(new HashSet<>())
                .build();

        //Act
        String expectedError = "A lista de ingredientes está vazia";

        //Assert
        IllegalStateException actualError = assertThrows(IllegalStateException.class, () -> pizza.removePizza());

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }
}
