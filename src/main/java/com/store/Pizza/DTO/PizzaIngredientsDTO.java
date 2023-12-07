package com.store.Pizza.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaIngredientsDTO extends PizzaDTO {
    private Set<IngredientsDTO> ingredientsIds;
}
