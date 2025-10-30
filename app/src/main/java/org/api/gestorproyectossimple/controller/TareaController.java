package org.api.gestorproyectossimple.controller;

import org.api.gestorproyectossimple.model.Tarea;
import org.api.gestorproyectossimple.service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    // GET → Listar todas las tareas
    @GetMapping
    public ResponseEntity<List<Tarea>> listar() {
        return ResponseEntity.ok(tareaService.listarTareas());
    }

    // GET → Buscar tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscarPorId(@PathVariable Long id) {
        return tareaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET → Listar tareas por proyecto
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<Tarea>> listarPorProyecto(@PathVariable Long proyectoId) {
        return ResponseEntity.ok(tareaService.listarPorProyecto(proyectoId));
    }

    // POST → Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.guardar(tarea));
    }

    // PUT → Actualizar tarea
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.actualizar(id, tarea));
    }

    // DELETE → Eliminar tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
