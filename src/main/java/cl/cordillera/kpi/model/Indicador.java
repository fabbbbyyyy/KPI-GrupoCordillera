package cl.cordillera.kpi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Genera getters, setters, toString, etc. automáticamente (Lombok)
@NoArgsConstructor
@AllArgsConstructor
public class Indicador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreKpi; // Ej: Ventas, Stock, Rentabilidad [3, 4]

    @Column (length = 10000)
    private String descripcion;

    @Column
    private Double valorObjetivo; // Meta a alcanzar [4]

    @Column
    private String categoria; // Ventas, Inventario, Logística o Finanzas [3]

    @Column
    private String unidadMedida; // Porcentaje, Pesos, Unidades (Dato lógico adicional)
}