package ws_work.api.domain.dtos;

import ws_work.api.domain.entities.BrandEntity;
import ws_work.api.domain.entities.ModelEntity;

public record CodigoDescricaoDto(
        Long codigo,
        String descricao
) {
    public CodigoDescricaoDto {
    }

    public CodigoDescricaoDto(ModelEntity modelEntity) {
        this(modelEntity.getId(), modelEntity.getNome());
    }

    public CodigoDescricaoDto(BrandEntity brandEntity) {
        this(brandEntity.getId(), brandEntity.getNomeMarca());
    }
}
