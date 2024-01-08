package com.store.Pizza.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaRequest {
    @Size(min = 4, max = 70, message = "O nome da pizza deve ter entre 4 e 70 caracteres")
    @NotBlank
    @Schema(description = "Nome da pizza", example = "Pepperoni")
    private String name;
    @Size(min = 4, max = 10, message = "O tamanho da pizza deve ter entre 4 e 10 caracteres")
    @NotBlank
    @Schema(description = "Tamanho da pizza", example = "Média")
    private String size;
    @DecimalMin(value = "0.0", message = "O valor deve ser maior que zero")
    @DecimalMax(value = "100.0", message = "O valor deve ser menor ou igual a 100")
    @Schema(description = "Preço da pizza", example = "25.00")
    @NotBlank
    private Double price;

}
