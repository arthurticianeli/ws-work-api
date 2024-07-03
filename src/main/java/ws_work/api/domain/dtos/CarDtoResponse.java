package ws_work.api.domain.dtos;


import ws_work.api.domain.entities.BrandEntity;
import ws_work.api.domain.entities.CarEntity;
import ws_work.api.domain.entities.ModelEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CarDtoResponse(
        Long id,
        LocalDateTime timestampCadastro,
        Long modeloId,
        Long ano,
        String combustivel,
        Long numPortas,
        String cor,
        String nomeModelo,
        BigDecimal valor,
        Long brandId,
        String nomeMarca
) {

    public CarDtoResponse(CarEntity carEntity, ModelEntity modelEntity, BrandEntity brandEntity) {
        this(
                carEntity.getId(),
                carEntity.getTimestampCadastro(),
                carEntity.getModeloId(),
                carEntity.getAno(),
                carEntity.getCombustivel(),
                carEntity.getNumPortas(),
                carEntity.getCor(),
                modelEntity.getNome(),
                modelEntity.getValorFipe(),
                brandEntity.getId(),
                brandEntity.getNomeMarca()
        );
    }
}
