package org.api.gestorproyectossimple.model;

import jakarta.persistence.*;
import lombok.*;

// Lombok genera los getters, setters, constructores y toString automáticamente
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private double salario;
}
