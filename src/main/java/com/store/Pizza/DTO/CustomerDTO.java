package com.store.Pizza.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    private String userName;

    private String userAddress;

    private Double balance;

}
