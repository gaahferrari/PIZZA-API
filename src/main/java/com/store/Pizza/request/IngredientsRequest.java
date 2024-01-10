package com.store.Pizza.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsRequest {

    @Size(min = 1, max = 50, message = "O nome do ingrediente deve ter entre 1 e 50 caracteres")
    @NotBlank(message = "O nome do ingrediente não pode estar em branco")
    @NotNull(message = "O nome do ingrediente não pode ser nulo")
    @Schema(description = "O nome do ingrediente", example = "Queijo")
    private String name;
}
