package ws_work.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ws_work.api.domain.dtos.CodigoDescricaoDto;
import ws_work.api.domain.dtos.ModelDtoRequest;
import ws_work.api.domain.dtos.ModelDtoResponse;
import ws_work.api.domain.entities.ModelEntity;
import ws_work.api.domain.repositories.BrandRepository;
import ws_work.api.domain.repositories.ModelRepository;
import ws_work.api.domain.services.ModelService;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelRepository repository;

    @Autowired
    private ModelService modelService;

    @PostMapping
    @Transactional
    public ResponseEntity<ModelDtoResponse> createModel(@RequestBody @Valid ModelDtoRequest modelDtoRequest, UriComponentsBuilder uriComponentsBuilder) {
        var model = modelService.createModel(modelDtoRequest);

        var uri = uriComponentsBuilder.path("/brand/{id}").buildAndExpand(model.id()).toUri();

        return ResponseEntity.created(uri).body(model);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ModelDtoResponse>> listModels(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(modelService.findAllPaginated(pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ModelDtoResponse> updateModel(@RequestBody @Valid ModelDtoRequest modelDtoRequest) {
        return ResponseEntity.ok(modelService.updateModel(modelDtoRequest));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
          modelService.deleteModel(id);
          return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDtoResponse> findModelById(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.findModelById(id));
    }

    @GetMapping
    public ResponseEntity<List<CodigoDescricaoDto>> getAllCodigoDescricao() {
        return ResponseEntity.ok(modelService.getAllCodigoDescricao());
    }

}
