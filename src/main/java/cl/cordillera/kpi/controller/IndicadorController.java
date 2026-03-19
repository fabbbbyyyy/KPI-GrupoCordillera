package cl.cordillera.kpi.controller;

import cl.cordillera.kpi.model.Indicador;
import cl.cordillera.kpi.service.IndicadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicadores")
@Tag(name = "Indicadores", description = "Operaciones CRUD para indicadores KPI")
public class IndicadorController {
    @Autowired
    private IndicadorService indicadorService;

    @GetMapping // Endpoint 1: Listado de indicadores para el Dashboard [8, 9]
    @Operation(summary = "Listar indicadores", description = "Obtiene todos los indicadores registrados")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public List<Indicador> listar() {
        return indicadorService.obtenerTodos();
    }

    @PostMapping // Endpoint 2: Creación de nuevos KPIs estratégicos [8, 9]
    @Operation(summary = "Crear indicador", description = "Crea un nuevo indicador KPI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicador creado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content)
    })
    public Indicador crear(@RequestBody Indicador indicador) {
        return indicadorService.guardar(indicador);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener indicador por id", description = "Busca un indicador por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicador encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Indicador.class))),
            @ApiResponse(responseCode = "404", description = "Indicador no encontrado", content = @Content)
    })
    public Indicador Obtener(@PathVariable Long id) {
        return indicadorService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar indicador", description = "Actualiza un indicador existente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicador actualizado"),
            @ApiResponse(responseCode = "404", description = "Indicador no encontrado", content = @Content)
    })
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
    @Operation(summary = "Eliminar indicador", description = "Elimina un indicador por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicador eliminado"),
            @ApiResponse(responseCode = "404", description = "Indicador no encontrado", content = @Content)
    })
    public void deleteIndicador(@Parameter(description = "ID del indicador") @PathVariable Long id) {
        indicadorService.deleteIndicador(id);
    }
}
