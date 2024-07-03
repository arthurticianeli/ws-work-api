package ws_work.api.domain.dtos;


import ws_work.api.domain.entities.BrandEntity;
import ws_work.api.domain.entities.ModelEntity;

import java.math.BigDecimal;

public record ModelDtoResponse(
        Long id,
        Long marcaId,
        String nome,
        BigDecimal valorFipe
) {

    public ModelDtoResponse(ModelEntity modelEntity) {
        this(
                modelEntity.getId(),
                modelEntity.getMarcaId(),
                modelEntity.getNome(),
                modelEntity.getValorFipe()
        );
    }
}
