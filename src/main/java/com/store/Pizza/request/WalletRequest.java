package com.store.Pizza.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {
    @DecimalMin(value = "0.0", message = "O valor deve ser maior que zero")
    @NotNull
    @Schema(description = "Saldo da carteira", example = "100.00")
    private Double balance;

    @Min(value = 1, message = "O ID do cliente deve ser no mínimo 1")
    @Max(Integer.MAX_VALUE)
    @NotNull
    @Schema(description = "ID do cliente", example = "12345")
    private Long customerId;

}
