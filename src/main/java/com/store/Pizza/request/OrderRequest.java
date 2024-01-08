package com.store.Pizza.request;

import com.store.Pizza.DTO.PizzaDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero")
    @NotBlank
    @Schema(description = "O valor total do pedido", example = "25.00")
    private Double orderTotalPrice;
    @Min(1)
    @NotBlank
    @Max(Integer.MAX_VALUE)
    @Schema(description = "ID do cliente", example = "12345")
    private Long customerId;

    @NotNull(message = "o usuário não pode ser nulo")
    @Size(min = 4, max = 70)
    @NotBlank
    @Schema(description = "Nome do cliente", example = "João da Silva")
    private String customerName;
    @NotNull(message = "A lista de pizzas não pode ser nula")
    @Size(min = 1, max = 20, message = "A lista de pizzas deve conter entre 1 e 20 elementos")
    @Valid
    @Schema(description = "Lista de IDs das pizzas", example = "[1, 2, 3]")
   private Set<PizzaDTO> pizzasListIds;

}
