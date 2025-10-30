package org.api.gestorproyectossimple.dto;

import lombok.Data;
import java.time.LocalDateTime;

// DTO del registro de horas trabajadas
@Data
public class RegistroHorasDTO {
    private Long id;
    private double horasTrabajadas;
    private LocalDateTime fechaRegistro;
    private Long empleadoId;
    private Long tareaId;
}
