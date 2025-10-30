package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.Proyecto;
import org.api.gestorproyectossimple.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> listarProyectos() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> buscarPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto actualizar(Long id, Proyecto nuevo) {
        return proyectoRepository.findById(id)
                .map(p -> {
                    p.setNombre(nuevo.getNombre());
                    p.setDescripcion(nuevo.getDescripcion());
                    p.setFechaInicio(nuevo.getFechaInicio());
                    p.setFechaFin(nuevo.getFechaFin());
                    return proyectoRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}
