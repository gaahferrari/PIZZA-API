package com.store.Pizza.services;

import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.exceptions.NotFoundException;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.mapper.PizzaMapper;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.request.PizzaRequest;
import com.store.Pizza.service.PizzaService;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {
    @Mock
    PizzaRepository pizzaRepository;

    @Mock
    IngredientsRepository ingredientsRepository;

    @InjectMocks
    @Spy
    PizzaService pizzaService;

    @Test
    public void shouldCreatePizza_whenRequestIsValid() {
        try (MockedStatic<PizzaMapper> pizzaMapper = mockStatic(PizzaMapper.class)) {
            //Arrange
            Pizza pizza = mock(Pizza.class);

            PizzaRequest pizzaRequest = mock(PizzaRequest.class);

            when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
            pizzaMapper.when(() -> PizzaMapper.toPizza(any(PizzaRequest.class))).thenReturn(pizza);

            //Act
            pizzaService.create(pizzaRequest);

            //Assert
            verify(pizzaRepository).save(pizza);
        }
    }

    @Test
    public void shouldCreateIngredients_whenRequestIsNotValid(){

        //Arrange

        PizzaRequest pizzaRequest = mock(PizzaRequest.class);

        when(pizzaRepository.save(any(Pizza.class))).thenReturn(null);

        String expectedError = "Erro ao criar uma nova pizza";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> pizzaService.create(pizzaRequest));

        //Assert
        assertEquals(expectedError, actualError.getMessage());


    }

    @Test
    public void shouldDeletePizza_whenRequestIsValid(){

        //Arrange
        Pizza pizza = mock(Pizza.class);

        when(pizzaRepository.findById(anyLong())).thenReturn(Optional.of(pizza));

        //Act
        pizzaService.deletePizza(pizza.getId());


        //Assert
        verify(pizza).removePizza();
        verify(pizzaRepository).delete(pizza);

    }

    @Test
    public void shouldDeleteCustomer_whenRequestIsNotValid(){

        //Arrange
        Pizza pizza = mock(Pizza.class);


        when(pizzaRepository.findById(any())).thenReturn(Optional.empty());
        String expectedError = "Erro ao deletar sua pizza";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> pizzaService.deletePizza(pizza.getId()));

        //Assert
        assertEquals(expectedError, actualError.getMessage());

    }

    @Test
    public void shouldAddIngredientToPizza_whenRequestIsValid() {
        try (MockedStatic<PizzaMapper> pizzaMapper = mockStatic(PizzaMapper.class)) {
            //Arrange
            Pizza pizza = mock(Pizza.class);

            Ingredients ingredients = mock(Ingredients.class);

            when(pizzaRepository.findById(anyLong())).thenReturn(Optional.of(pizza));
            when(ingredientsRepository.findById(anyLong())).thenReturn(Optional.of(ingredients));
            pizzaMapper.when(() -> PizzaMapper.toPizza(any(PizzaRequest.class))).thenReturn(pizza);

            //Act
            pizzaService.addIngredientsToPizza(pizza.getId(), ingredients.getId());

            //Assert
            verify(pizzaRepository).save(pizza);
            verify(pizza).addIngredient(ingredients);
            verify(pizzaRepository).findById(pizza.getId());
            verify(ingredientsRepository).findById(ingredients.getId());
        }
    }

    @Test
    public void shouldAddIngredientToPizza_whenRequestIsNotValid(){

        //Arrange

        Pizza pizza = mock(Pizza.class);
        Ingredients ingredients = mock(Ingredients.class);

        when(pizzaRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(ingredientsRepository.findById(anyLong())).thenReturn(Optional.empty());

        String expectedError = "Pizza ou ingrediente não foi encontrado";
        //Act
        NotFoundException actualError = assertThrows(NotFoundException.class, () -> pizzaService.addIngredientsToPizza(pizza.getId(), ingredients.getId()));

        //Assert
        assertEquals(expectedError, actualError.getMessage());


    }


    @Test
    public void shouldReturnAllPizzas_whenListIsNotEmpty() {
        try (MockedStatic<PizzaMapper> pizzaMapper = mockStatic(PizzaMapper.class)) {
            // Arrange
            Pizza pizza = mock(Pizza.class);
            List<Pizza> pizzas = Collections.singletonList(pizza);


            when(pizzaRepository.findAll()).thenReturn(pizzas);
            pizzaMapper.when(() -> PizzaMapper.toPizza(any(PizzaRequest.class))).thenReturn(pizza);

            // Act
            pizzaService.getAll();

            // Assert
            // verify(customerRepository, times(1)).findAll();
            verify(pizzaRepository).findAll();
        }
    }

    @Test
    public void shouldReturnAllPizzas_whenListIsEmpty() {

        //Arrange
        when(pizzaRepository.findAll()).thenReturn(Collections.emptyList());
        String expectedError = "A lista de Pizza está vazia";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> pizzaService.getAll());

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }



}
