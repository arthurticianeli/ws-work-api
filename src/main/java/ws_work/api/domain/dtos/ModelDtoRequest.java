package ws_work.api.domain.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ModelDtoRequest(
        Long id,
        @NotNull
        Long marcaId,
        @NotNull
        String nome,
        @NotNull
        BigDecimal valorFipe
) {}
