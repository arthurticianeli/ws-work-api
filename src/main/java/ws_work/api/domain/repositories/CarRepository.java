package ws_work.api.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws_work.api.domain.entities.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    @Query("SELECT c " +
            "FROM car c " +
            "JOIN model m ON c.modeloId = m.id " +
            "JOIN brand b ON m.marcaId = b.id " +
            "WHERE (:marcaId IS NULL OR b.id = :marcaId) AND (:modeloId IS NULL OR m.id = :modeloId)")
    Page<?> findAllByMarcaIdAndModeloId(@Param("marcaId") Long marcaId, @Param("modeloId") Long modeloId, Pageable pageable);
}
