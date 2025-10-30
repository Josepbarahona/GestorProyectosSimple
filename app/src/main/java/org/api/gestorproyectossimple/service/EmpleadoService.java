package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.Empleado;
import org.api.gestorproyectossimple.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    // Constructor con inyección de dependencias
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // Obtener todos los empleados
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    // Buscar un empleado por ID
    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    // Guardar un nuevo empleado
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Actualizar un empleado existente
    public Empleado actualizar(Long id, Empleado nuevo) {
        return empleadoRepository.findById(id)
                .map(e -> {
                    e.setNombre(nuevo.getNombre());
                    e.setCargo(nuevo.getCargo());
                    e.setEmail(nuevo.getEmail());
                    e.setSalario(nuevo.getSalario());
                    return empleadoRepository.save(e);
                })
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    // Eliminar un empleado por ID
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
