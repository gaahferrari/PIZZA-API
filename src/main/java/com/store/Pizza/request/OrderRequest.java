package com.store.Pizza.request;

import com.store.Pizza.DTO.PizzaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  //  private Long id;
    private Double orderTotalPrice;
    private Long customerId;
    private String customerName;
   private Set<PizzaDTO> pizzasListIds;

}
