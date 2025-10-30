package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.Tarea;
import org.api.gestorproyectossimple.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public List<Tarea> listarPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId);
    }

    public List<Tarea> listarPorEmpleado(Long empleadoId) {
        return tareaRepository.findByEmpleadoId(empleadoId);
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(Long id, Tarea nueva) {
        return tareaRepository.findById(id)
                .map(t -> {
                    t.setTitulo(nueva.getTitulo());
                    t.setDescripcion(nueva.getDescripcion());
                    t.setFechaLimite(nueva.getFechaLimite());
                    t.setCompletada(nueva.isCompletada());
                    return tareaRepository.save(t);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}
