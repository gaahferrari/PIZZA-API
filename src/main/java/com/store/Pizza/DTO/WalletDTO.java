package com.store.Pizza.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {

    private Long id;

    private Double balance;

    private Long customerId;

    private String customerUsername;

    private String customerAddress;
}
