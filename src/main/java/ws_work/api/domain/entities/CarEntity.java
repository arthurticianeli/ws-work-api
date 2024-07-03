package ws_work.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import ws_work.api.domain.dtos.CarDtoRequest;

import java.time.LocalDateTime;

@Table(name = "cars")
@Entity(name = "car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestampCadastro;
    @Column(name = "modeloId", nullable = false)
    private Long modeloId;
    private Long ano;
    private String combustivel;
    private Long numPortas;
    private String cor;

    public CarEntity(CarDtoRequest carDtoRequest){
        this.timestampCadastro = LocalDateTime.now();
        this.modeloId = carDtoRequest.modeloId();
        this.ano = carDtoRequest.ano();
        this.combustivel = carDtoRequest.combustivel();
        this.numPortas = carDtoRequest.numPortas();
        this.cor = carDtoRequest.cor();
    }

    public void update(CarDtoRequest carDtoRequest){
        this.timestampCadastro = carDtoRequest.timestampCadastro();
        this.modeloId = carDtoRequest.modeloId();
        this.ano = carDtoRequest.ano();
        this.combustivel = carDtoRequest.combustivel();
        this.numPortas = carDtoRequest.numPortas();
        this.cor = carDtoRequest.cor();
    }
}