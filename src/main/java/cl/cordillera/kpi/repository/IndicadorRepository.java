package cl.cordillera.kpi.repository;

import cl.cordillera.kpi.model.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorRepository extends JpaRepository<Indicador, Long> {
    // Permite realizar operaciones CRUD automáticamente [4]
}