package org.api.gestorproyectossimple.dto;

import lombok.Data;
import java.time.LocalDate;

// DTO simplificado del proyecto
@Data
public class ProyectoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
