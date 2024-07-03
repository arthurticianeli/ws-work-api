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
import ws_work.api.domain.dtos.BrandDtoRequest;
import ws_work.api.domain.dtos.BrandDtoResponse;
import ws_work.api.domain.dtos.CodigoDescricaoDto;
import ws_work.api.domain.entities.BrandEntity;
import ws_work.api.domain.repositories.BrandRepository;
import ws_work.api.domain.services.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandRepository repository;

    @Autowired
    private BrandService brandService;

    @PostMapping
    @Transactional
    public ResponseEntity<BrandDtoResponse> createBrand(@RequestBody @Valid BrandDtoRequest brandDtoRequest, UriComponentsBuilder uriComponentsBuilder) {
        var brand = brandService.createBrand(brandDtoRequest);

        var uri = uriComponentsBuilder.path("/brand/{id}").buildAndExpand(brand.id()).toUri();

        return ResponseEntity.created(uri).body(brand);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<BrandDtoResponse>> listBrands(
            @RequestParam(name = "nomeMarca", required = false) String nomeMarca,
            @PageableDefault(size = 10, sort = {"nomeMarca"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(brandService.findAllBrandsByNomeMarca(nomeMarca, pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<BrandDtoResponse> updateBrand(@RequestBody @Valid BrandDtoRequest brandDtoRequest) {
             return ResponseEntity.ok(brandService.updateBrand(brandDtoRequest));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
       brandService.deleteBrand(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDtoResponse> findBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findBrandById(id));
    }

    @GetMapping
    public ResponseEntity<List<CodigoDescricaoDto>> getAllCodigoDescricao() {
        return ResponseEntity.ok(brandService.getAllCodigoDescricao());
    }

}
