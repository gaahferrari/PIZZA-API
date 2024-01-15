package com.store.Pizza.services;

import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.mapper.IngredientsMapper;
import com.store.Pizza.mapper.PizzaMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.request.PizzaRequest;
import com.store.Pizza.service.CustomerService;
import com.store.Pizza.service.PizzaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
