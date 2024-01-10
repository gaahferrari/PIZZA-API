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
    @DecimalMin(value = "0.0", message = "O valor deve ser maior que zero")
    @NotNull(message = "O preço total do pedido não pode ser nulo")
    @Schema(description = "O valor total do pedido", example = "25.00")
    private Double orderTotalPrice;
    @Min(value = 1, message = "O ID do usuário não ser menor que 1")
    @NotNull(message = "O ID do usuário não pode ser nulo")
    @Max(Integer.MAX_VALUE)
    @Schema(description = "ID do cliente", example = "12345")
    private Long customerId;

    @Size(min = 4, max = 70, message = "O nome do usuário dve conter entre 4 e 70 caracteres")
    @Schema(description = "Nome do cliente", example = "João da Silva")
    private String customerName;

    @Size(min = 1, max = 20, message = "A lista de pizzas deve conter entre 1 e 20 elementos")
    @Valid
    @Schema(description = "Lista de IDs das pizzas", example = "[1, 2, 3]")
    private Set<PizzaDTO> pizzasListIds;

}
