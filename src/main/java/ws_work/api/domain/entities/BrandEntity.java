package ws_work.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ws_work.api.domain.dtos.BrandDtoRequest;

@Table(name = "brands")
@Entity(name = "brand")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMarca;

    public BrandEntity(BrandDtoRequest brandDtoRequest) {
        this.nomeMarca = brandDtoRequest.nomeMarca();
    }

    public void update(BrandDtoRequest brandDtoRequest) {
        this.nomeMarca = brandDtoRequest.nomeMarca();
    }
}
