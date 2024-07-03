package ws_work.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ws_work.api.domain.dtos.ModelDtoRequest;

import java.math.BigDecimal;

@Table(name = "models")
@Entity(name = "model")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "marcaId", nullable = false)
    private Long marcaId;
    private String nome;
    private BigDecimal valorFipe;

    public ModelEntity(ModelDtoRequest modelDtoRequest){
        this.marcaId = modelDtoRequest.marcaId();
        this.nome = modelDtoRequest.nome();
        this.valorFipe = modelDtoRequest.valorFipe();
    }

    public void update(ModelDtoRequest modelDtoRequest){
        this.marcaId = modelDtoRequest.marcaId();
        this.nome = modelDtoRequest.nome();
        this.valorFipe = modelDtoRequest.valorFipe();
    }

}