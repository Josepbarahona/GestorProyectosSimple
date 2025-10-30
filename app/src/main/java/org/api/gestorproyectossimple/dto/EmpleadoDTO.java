package org.api.gestorproyectossimple.dto;

import lombok.Data;

// DTO para enviar/recibir datos básicos de un empleado
@Data
public class EmpleadoDTO {
    private Long id;
    private String nombre;
    private String cargo;
    private String email;
    private double salario;
}
