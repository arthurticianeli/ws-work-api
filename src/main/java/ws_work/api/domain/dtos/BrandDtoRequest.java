package ws_work.api.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record BrandDtoRequest(
        Long id,
        @NotNull
        String nomeMarca
) {}
