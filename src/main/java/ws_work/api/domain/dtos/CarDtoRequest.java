package ws_work.api.domain.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CarDtoRequest(
        Long id,
        LocalDateTime timestampCadastro,
        @NotNull
        Long modeloId,
        @NotNull
        Long ano,
        @NotNull
        String combustivel,
        @NotNull
        Long numPortas,
        @NotNull
        String cor
) {
}
