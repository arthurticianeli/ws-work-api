package ws_work.api.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ws_work.api.domain.entities.CarEntity;
import ws_work.api.domain.entities.ModelEntity;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
}
