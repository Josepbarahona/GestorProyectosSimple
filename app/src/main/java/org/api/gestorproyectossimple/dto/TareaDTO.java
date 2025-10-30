package org.api.gestorproyectossimple.dto;

import lombok.Data;
import java.time.LocalDate;

// DTO de la entidad Tarea
@Data
public class TareaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private boolean completada;
    private Long proyectoId;
    private Long empleadoId;
}
