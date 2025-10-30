package org.api.gestorproyectossimple.repository;

import org.api.gestorproyectossimple.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Buscar todas las tareas de un proyecto
    List<Tarea> findByProyectoId(Long proyectoId);

    // Buscar todas las tareas asignadas a un empleado
    List<Tarea> findByEmpleadoId(Long empleadoId);
}
