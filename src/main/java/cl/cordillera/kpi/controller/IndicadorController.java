package cl.cordillera.kpi.controller;

import cl.cordillera.kpi.model.Indicador;
import cl.cordillera.kpi.service.IndicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicadores")
public class IndicadorController {
    @Autowired
    private IndicadorService indicadorService;

    @GetMapping // Endpoint 1: Listado de indicadores para el Dashboard [8, 9]
    public List<Indicador> listar() {
        return indicadorService.obtenerTodos();
    }

    @PostMapping // Endpoint 2: Creación de nuevos KPIs estratégicos [8, 9]
    public Indicador crear(@RequestBody Indicador indicador) {
        return indicadorService.guardar(indicador);
    }
    @GetMapping("/{id}")
    public Indicador Obtener(@PathVariable Long id) {
        return indicadorService.obtenerPorId(id);
    }
    @PutMapping("/{id}")
    public Indicador updateIndicador(@PathVariable Long id, @RequestBody Indicador indicador) {
        Indicador existingIndicador = indicadorService.obtenerPorId(id);
        if (existingIndicador != null) {
            existingIndicador.setNombreKpi(indicador.getNombreKpi());
            existingIndicador.setDescripcion(indicador.getDescripcion());
            existingIndicador.setCategoria(indicador.getCategoria());
            existingIndicador.setUnidadMedida(indicador.getUnidadMedida());
            existingIndicador.setValorObjetivo(indicador.getValorObjetivo());
            existingIndicador.setCategoria(indicador.getCategoria());
            return indicadorService.guardar(existingIndicador);
        }
        return null;
    }
    @DeleteMapping("/{id}")
   
    public void deleteIndicador(@PathVariable Long id) {
        indicadorService.deleteIndicador(id);
    }
}
