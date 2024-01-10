package com.store.Pizza.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 6, max = 70, message = "O nome deve ter entre 6 e 70 caracteres")
    @NotBlank(message = "O nome não pode estar em branco")
    @NotNull(message = "O nome não pode ser nulo")
    @Schema(description = "Nome completo do usuário", example = "José dos Santos")
    private String userName;

    @Size(min = 4, max = 100, message = "O endereço deve ter entre 4 e 100 caracteres")
    @NotBlank(message = "O endereço não pode estar em branco")
    @NotNull(message = "O engereço não pode ser nulo")
    @Schema(description = "Endereço do usuário", example = "Rua das Flores, 123")
    private String userAddress;

    @Valid
    @Schema(description = "Lista de IDs dos pedidos", example = "[1, 2, 3]")
    private List<Long> ordersListId;
    @Valid
    @Schema(description = "ID da carteira do usuário", example = "12345")
    private Long walletId;
}
