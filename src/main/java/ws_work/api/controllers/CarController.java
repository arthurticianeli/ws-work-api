package ws_work.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ws_work.api.domain.dtos.CarDtoRequest;
import ws_work.api.domain.dtos.CarDtoResponse;
import ws_work.api.domain.services.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    @Transactional
    public ResponseEntity<CarDtoResponse> createCar(@RequestBody @Valid CarDtoRequest carDtoRequest, UriComponentsBuilder uriComponentsBuilder) {
        var car = carService.createCar(carDtoRequest);

        var uri = uriComponentsBuilder.path("/car/{id}").buildAndExpand(car.id()).toUri();

        return ResponseEntity.created(uri).body(car);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<?>> listCars(
            @RequestParam(name = "page", required = false) String page,
            @RequestParam(name = "pageSize", required = false) String pageSize,
            @RequestParam(name = "brand", required = false) Long brandId,
            @RequestParam(name = "modelo", required = false) Long modeloId
    ) {
        Pageable pageable = PageRequest.of(
                page != null ? Integer.parseInt(page) - 1 : 0,
                pageSize != null ? Integer.parseInt(pageSize) : 10,
                Sort.by("modeloId")
        );
        return ResponseEntity.ok(carService.findAllCarsByMarcaId(brandId, modeloId, pageable));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<CarDtoResponse> updateCar(@RequestBody @Valid CarDtoRequest carDtoRequest) {
        return ResponseEntity.ok(carService.updateCar(carDtoRequest));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDtoResponse> findCarById(@PathVariable Long id) {

        return ResponseEntity.ok(carService.findCarById(id));
    }

}
