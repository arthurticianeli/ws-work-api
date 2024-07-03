package ws_work.api.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ws_work.api.domain.entities.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    Page<BrandEntity> findByNomeMarcaContainingIgnoreCase(String nomeMarca, Pageable pageable);
}