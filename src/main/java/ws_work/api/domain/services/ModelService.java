package ws_work.api.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ws_work.api.domain.dtos.CodigoDescricaoDto;
import ws_work.api.domain.dtos.ModelDtoRequest;
import ws_work.api.domain.dtos.ModelDtoResponse;
import ws_work.api.domain.entities.ModelEntity;
import ws_work.api.domain.repositories.ModelRepository;
import ws_work.api.infra.errorsHandlers.ErrorsHandler;

import java.util.List;

@Service
public class ModelService {


    @Autowired
    private ModelRepository modelRepository;


    public ModelDtoResponse createModel(ModelDtoRequest modelDtoRequest) {
        var model = new ModelEntity(modelDtoRequest);
        modelRepository.save(new ModelEntity(modelDtoRequest));

        return new ModelDtoResponse(model);
    }

    public Page<ModelDtoResponse> findAllPaginated(Pageable pageable) {
        Page<ModelEntity> modelEntities = modelRepository.findAll(pageable);
        return modelEntities.map(ModelDtoResponse::new);
    }

    public List<CodigoDescricaoDto> getAllCodigoDescricao() {
        List<ModelEntity> modelEntities = modelRepository.findAll();
        return modelEntities.stream().map(CodigoDescricaoDto::new).toList();
    }

    public ModelDtoResponse updateModel(ModelDtoRequest modelDtoRequest) {
        if(modelDtoRequest.id() == null) throw new ErrorsHandler.MissingIdException();

        var modelEntity = modelRepository.getReferenceById(modelDtoRequest.id());
        modelEntity.update(modelDtoRequest);
        modelRepository.save(modelEntity);

        return new ModelDtoResponse(modelEntity);
    }

    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }

    public ModelDtoResponse findModelById(Long id) {
        return new ModelDtoResponse(modelRepository.getReferenceById(id));
    }

}
