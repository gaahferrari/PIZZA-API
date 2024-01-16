package com.store.Pizza.services;

import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Ingredients;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.mapper.IngredientsMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.IngredientsRepository;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.request.IngredientsRequest;
import com.store.Pizza.service.CustomerService;
import com.store.Pizza.service.IngredientsService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IngredientsServiceTest {
    @Mock
    IngredientsRepository ingredientsRepository;

    @InjectMocks
    @Spy
    IngredientsService ingredientsService;

    @Test
    public void shouldCreateIngredients_whenRequestIsValid() {
        try (MockedStatic<IngredientsMapper> ingredientsMapper = mockStatic(IngredientsMapper.class)) {
            //Arrange
            Ingredients ingredients = mock(Ingredients.class);

            IngredientsRequest ingredientsRequest = mock(IngredientsRequest.class);

            when(ingredientsRepository.save(any(Ingredients.class))).thenReturn(ingredients);
            ingredientsMapper.when(() -> IngredientsMapper.toIngredients(any(IngredientsRequest.class))).thenReturn(ingredients);

            //Act
            ingredientsService.create(ingredientsRequest);

            //Assert
            verify(ingredientsRepository).save(ingredients);
        }
    }

    @Test
    public void shouldCreateIngredients_whenRequestIsNotValid(){

        //Arrange

        IngredientsRequest ingredientsRequest = mock(IngredientsRequest.class);

        when(ingredientsRepository.save(any(Ingredients.class))).thenReturn(null);

        String expectedError = "Erro ao criar um novo ingrediente";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> ingredientsService.create(ingredientsRequest));

        //Assert
        assertEquals(expectedError, actualError.getMessage());


    }

    @Test
    public void shouldDeleteIngredient_whenRequestIsValid(){

        //Arrange
        Ingredients ingredients = mock(Ingredients.class);

        when(ingredientsRepository.findById(anyLong())).thenReturn(Optional.of(ingredients));

        //Act
        ingredientsService.deleteIngredient(ingredients.getId());

        //Assert
        verify(ingredientsRepository).delete(ingredients);

    }

    @Test
    public void shouldDeleteIngredient_whenRequestIsNotValid(){

        //Arrange
        Ingredients ingredients = mock(Ingredients.class);

        when(ingredientsRepository.findById(any())).thenReturn(Optional.empty());
        String expectedError = "Erro ao deletar o ingrediente. Ele pode estar vinculado a uma pizza";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> ingredientsService.deleteIngredient(ingredients.getId()));

        //Assert
        assertEquals(expectedError, actualError.getMessage());

    }

    @Test
    public void shouldReturnAllIngredients_whenListIsNotEmpty() {
        try (MockedStatic<IngredientsMapper> ingredientsMapper = mockStatic(IngredientsMapper.class)) {
            // Arrange
            Ingredients ingredient = mock(Ingredients.class);
            List<Ingredients> ingredients = Collections.singletonList(ingredient);


            when(ingredientsRepository.findAll()).thenReturn(ingredients);
            ingredientsMapper.when(() -> IngredientsMapper.toIngredients(any(IngredientsRequest.class))).thenReturn(ingredient);

            // Act
            ingredientsService.getAll();

            // Assert
            // verify(customerRepository, times(1)).findAll();
            verify(ingredientsRepository).findAll();
        }
    }

    @Test
    public void shouldReturnAllIngredients_whenListIsEmpty() {

        //Arrange
        when(ingredientsRepository.findAll()).thenReturn(Collections.emptyList());
        String expectedError = "A lista de ingredientes está vazia";
        //Act
        BadRequestException actualError = assertThrows(BadRequestException.class, () -> ingredientsService.getAll());

        //Assert
        assertEquals(expectedError, actualError.getMessage());
    }


}
