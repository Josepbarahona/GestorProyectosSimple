package org.api.gestorproyectossimple.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 500)
    private String descripcion;

    private LocalDate fechaLimite;

    private boolean completada;

    // Muchas tareas pueden pertenecer a un proyecto
    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    // Una tarea puede estar asignada a un empleado
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}
