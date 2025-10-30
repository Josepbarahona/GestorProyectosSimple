package org.api.gestorproyectossimple.repository;

import org.api.gestorproyectossimple.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Puedes agregar consultas personalizadas si lo necesitas, por ejemplo:
    // Optional<Empleado> findByEmail(String email);
}
