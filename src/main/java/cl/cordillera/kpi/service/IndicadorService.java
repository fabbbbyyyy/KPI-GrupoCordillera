package cl.cordillera.kpi.service;

import cl.cordillera.kpi.model.Indicador;
import cl.cordillera.kpi.repository.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class IndicadorService {
    @Autowired
    private IndicadorRepository indicadorRepository;

    public List<Indicador> obtenerTodos() {
        return indicadorRepository.findAll();
    }

    public Indicador guardar(Indicador indicador) {
        return indicadorRepository.save(indicador);
    }
    public Indicador obtenerPorId (long id){
        return indicadorRepository.findById(id).orElse(null);
    }
    public void deleteIndicador(Long id){
        indicadorRepository.deleteById(id);
    }
}
