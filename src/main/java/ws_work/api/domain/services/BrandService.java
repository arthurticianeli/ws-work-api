package ws_work.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ws_work.api.domain.dtos.BrandDtoRequest;
import ws_work.api.domain.dtos.BrandDtoResponse;
import ws_work.api.domain.dtos.CodigoDescricaoDto;
import ws_work.api.domain.entities.BrandEntity;
import ws_work.api.domain.repositories.BrandRepository;
import ws_work.api.infra.errorsHandlers.ErrorsHandler;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public BrandDtoResponse createBrand(BrandDtoRequest brandDtoRequest) {
        var model = new BrandEntity(brandDtoRequest);
        brandRepository.save(new BrandEntity(brandDtoRequest));

        return new BrandDtoResponse(model);
    }

    public Page<BrandDtoResponse> findAllBrandsByNomeMarca(String nomeMarca, Pageable pageable) {
        if(nomeMarca == null || nomeMarca.isBlank())
            return brandRepository.findAll(pageable).map(BrandDtoResponse::new);

        Page<BrandEntity> modelEntities = brandRepository.findByNomeMarcaContainingIgnoreCase(nomeMarca, pageable);
        return modelEntities.map(BrandDtoResponse::new);
    }

    public List<CodigoDescricaoDto> getAllCodigoDescricao() {
        List<BrandEntity> brandEntities = brandRepository.findAll();
        return brandEntities.stream().map(CodigoDescricaoDto::new).toList();
    }

    public BrandDtoResponse updateBrand(BrandDtoRequest brandDtoRequest) {
        if (brandDtoRequest.id() == null) throw new ErrorsHandler.MissingIdException();

        var BrandEntity = brandRepository.getReferenceById(brandDtoRequest.id());
        BrandEntity.update(brandDtoRequest);
        brandRepository.save(BrandEntity);

        return new BrandDtoResponse(BrandEntity);
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public BrandDtoResponse findBrandById(Long id) {
        return new BrandDtoResponse(brandRepository.getReferenceById(id));
    }

}
