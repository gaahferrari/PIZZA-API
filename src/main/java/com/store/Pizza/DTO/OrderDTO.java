package com.store.Pizza.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Double orderTotalPrice;

    private Long customerId;

    private String name;

    private String address;
}
