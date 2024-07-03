package ws_work.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ws_work.api.domain.dtos.CarDtoResponse;
import ws_work.api.domain.dtos.CarDtoRequest;
import ws_work.api.domain.entities.BrandEntity;
import ws_work.api.domain.entities.CarEntity;
import ws_work.api.domain.entities.ModelEntity;
import ws_work.api.domain.repositories.BrandRepository;
import ws_work.api.domain.repositories.CarRepository;
import ws_work.api.domain.repositories.ModelRepository;
import ws_work.api.infra.errorsHandlers.ErrorsHandler;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public CarDtoResponse createCar(CarDtoRequest carDtoRequest) {
        var car = new CarEntity(carDtoRequest);
        carRepository.save(new CarEntity(carDtoRequest));

        var model = modelRepository.getReferenceById(carDtoRequest.modeloId());
        var brand = brandRepository.getReferenceById(model.getMarcaId());

        return new CarDtoResponse(car, model, brand);
    }

    public CarDtoResponse updateCar(CarDtoRequest carDtoRequest) {
        if (carDtoRequest.id() == null) throw new ErrorsHandler.MissingIdException();

        var carEntity = carRepository.getReferenceById(carDtoRequest.id());
        carEntity.update(carDtoRequest);
        carRepository.save(carEntity);

        var model = modelRepository.getReferenceById(carEntity.getModeloId());
        var brand = brandRepository.getReferenceById(model.getMarcaId());

        return new CarDtoResponse(carEntity, model, brand);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public CarDtoResponse findCarById(Long id) {
        var car = carRepository.getReferenceById(id);
        var model = modelRepository.getReferenceById(car.getModeloId());
        var brand = brandRepository.getReferenceById(model.getMarcaId());
        return new CarDtoResponse(car, model, brand);
    }

    public Page<CarDtoResponse> findAllCarsByMarcaId(Long marcaId, Long modeloId, Pageable pageable) {

        Page<?> carList = carRepository.findAllByMarcaIdAndModeloId(marcaId, modeloId, pageable);

        List<CarDtoResponse> carDtoResponseList = carList.stream().map(car -> {
            CarEntity carEntity = (CarEntity) car;
            ModelEntity modelEntity = modelRepository.getReferenceById(carEntity.getModeloId());
            BrandEntity brandEntity = brandRepository.getReferenceById(modelEntity
                    .getMarcaId());
            return new CarDtoResponse(carEntity, modelEntity, brandEntity);
        }).collect(Collectors.toList());

        return new PageImpl<>(carDtoResponseList, pageable, carList.getTotalElements());
    }
}
