package org.api.gestorproyectossimple.controller;

import org.api.gestorproyectossimple.model.RegistroHoras;
import org.api.gestorproyectossimple.service.RegistroHorasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrohoras")
public class RegistroHorasController {

    private final RegistroHorasService registroHorasService;

    public RegistroHorasController(RegistroHorasService registroHorasService) {
        this.registroHorasService = registroHorasService;
    }

    // GET → Listar todos los registros
    @GetMapping
    public ResponseEntity<List<RegistroHoras>> listar() {
        return ResponseEntity.ok(registroHorasService.listarTodos());
    }

    // POST → Registrar horas trabajadas
    @PostMapping
    public ResponseEntity<RegistroHoras> crear(@RequestBody RegistroHoras registro) {
        return ResponseEntity.ok(registroHorasService.guardar(registro));
    }

    // GET → Listar horas por tarea
    @GetMapping("/tarea/{id}")
    public ResponseEntity<List<RegistroHoras>> listarPorTarea(@PathVariable Long id) {
        return ResponseEntity.ok(registroHorasService.listarPorTarea(id));
    }

    // GET → Listar horas por empleado
    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<RegistroHoras>> listarPorEmpleado(@PathVariable Long id) {
        return ResponseEntity.ok(registroHorasService.listarPorEmpleado(id));
    }

    // GET → Calcular total de horas por tarea
    @GetMapping("/tarea/{id}/total")
    public ResponseEntity<Double> totalPorTarea(@PathVariable Long id) {
        return ResponseEntity.ok(registroHorasService.calcularHorasPorTarea(id));
    }
}
