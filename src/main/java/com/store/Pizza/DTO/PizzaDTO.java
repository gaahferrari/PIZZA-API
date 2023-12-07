package com.store.Pizza.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {
    private Long id;

    private String name;

    private String size;

    private Double price;
}
