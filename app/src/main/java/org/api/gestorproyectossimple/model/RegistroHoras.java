package org.api.gestorproyectossimple.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "registro_horas")
public class RegistroHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double horasTrabajadas;

    private LocalDateTime fechaRegistro;

    // Relación con Empleado: quién registró las horas
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    // Relación con Tarea: en qué tarea trabajó
    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;
}
