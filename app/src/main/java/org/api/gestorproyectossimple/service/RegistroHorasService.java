package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.RegistroHoras;
import org.api.gestorproyectossimple.repository.RegistroHorasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroHorasService {

    private final RegistroHorasRepository registroHorasRepository;

    public RegistroHorasService(RegistroHorasRepository registroHorasRepository) {
        this.registroHorasRepository = registroHorasRepository;
    }

    // Registrar horas trabajadas
    public RegistroHoras guardar(RegistroHoras registroHoras) {
        return registroHorasRepository.save(registroHoras);
    }

    // Consultar todos los registros
    public List<RegistroHoras> listarTodos() {
        return registroHorasRepository.findAll();
    }

    // Consultar horas por tarea
    public List<RegistroHoras> listarPorTarea(Long tareaId) {
        return registroHorasRepository.findByTareaId(tareaId);
    }

    // Consultar horas por empleado
    public List<RegistroHoras> listarPorEmpleado(Long empleadoId) {
        return registroHorasRepository.findByEmpleadoId(empleadoId);
    }

    // Calcular total de horas trabajadas por tarea
    public double calcularHorasPorTarea(Long tareaId) {
        return registroHorasRepository.findByTareaId(tareaId)
                .stream()
                .mapToDouble(RegistroHoras::getHorasTrabajadas)
                .sum();
    }

    // Calcular total de horas trabajadas por proyecto (ejemplo de lógica de negocio)
    public double calcularHorasPorProyecto(List<Long> tareaIds) {
        return tareaIds.stream()
                .flatMap(id -> registroHorasRepository.findByTareaId(id).stream())
                .mapToDouble(RegistroHoras::getHorasTrabajadas)
                .sum();
    }
}
