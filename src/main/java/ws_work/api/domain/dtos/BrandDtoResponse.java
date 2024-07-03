package ws_work.api.domain.dtos;


import ws_work.api.domain.entities.BrandEntity;
import ws_work.api.domain.entities.CarEntity;

public record BrandDtoResponse(
        Long id,
        String nomeMarca
) {

    public BrandDtoResponse(BrandEntity brandEntity) {
        this(
                brandEntity.getId(),
                brandEntity.getNomeMarca()
        );
    }
}
