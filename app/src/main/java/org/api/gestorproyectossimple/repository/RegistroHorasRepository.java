package org.api.gestorproyectossimple.repository;

import org.api.gestorproyectossimple.model.RegistroHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegistroHorasRepository extends JpaRepository<RegistroHoras, Long> {

    // Consultar las horas registradas por tarea
    List<RegistroHoras> findByTareaId(Long tareaId);

    // Consultar las horas registradas por empleado
    List<RegistroHoras> findByEmpleadoId(Long empleadoId);
}
