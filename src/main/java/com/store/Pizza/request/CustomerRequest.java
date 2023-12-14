package com.store.Pizza.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private String userName;

    private String userAddress;

    private List<Long> ordersListId;

    private Long walletId;
}
