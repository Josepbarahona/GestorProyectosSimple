package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.Empleado;
import org.api.gestorproyectossimple.model.Proyecto;
import org.api.gestorproyectossimple.model.Tarea;
import org.api.gestorproyectossimple.model.RegistroHoras;
import org.api.gestorproyectossimple.repository.ProyectoRepository;
import org.api.gestorproyectossimple.repository.RegistroHorasRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProyectoServiceTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @Mock
    private RegistroHorasRepository registroHorasRepository;

    @InjectMocks
    private ProyectoService proyectoService;


    @Test
    void testGuardarProyecto() {
        Proyecto proyecto = Proyecto.builder()
                .nombre("Proyecto A")
                .descripcion("Test")
                .build();

        when(proyectoRepository.save(proyecto)).thenReturn(proyecto);

        Proyecto result = proyectoService.guardar(proyecto);

        assertNotNull(result);
        assertEquals("Proyecto A", result.getNombre());
        verify(proyectoRepository).save(proyecto);
    }


    @Test
    void testObtenerProyectoPorId() {
        Proyecto proyecto = Proyecto.builder()
                .id(1L)
                .nombre("Proyecto Test")
                .build();

        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        Optional<Proyecto> result = proyectoService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Proyecto Test", result.get().getNombre());
        verify(proyectoRepository).findById(1L);
    }


    @Test
    void testListarProyectos() {
        Proyecto p1 = new Proyecto();
        Proyecto p2 = new Proyecto();

        when(proyectoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Proyecto> result = proyectoService.listarProyectos();

        assertEquals(2, result.size());
        verify(proyectoRepository).findAll();
    }


    @Test
    void testCalcularHorasPorProyecto() {

        // Proyecto
        Proyecto proyecto = Proyecto.builder()
                .id(10L)
                .nombre("Proyecto X")
                .build();

        // Tareas asociadas al proyecto
        Tarea tarea1 = Tarea.builder()
                .id(100L)
                .proyecto(proyecto)
                .build();

        Tarea tarea2 = Tarea.builder()
                .id(101L)
                .proyecto(proyecto)
                .build();

        // Registro de horas reales
        RegistroHoras r1 = RegistroHoras.builder()
                .id(1L)
                .tarea(tarea1)
                .horasTrabajadas(2.5)
                .fechaRegistro(LocalDateTime.now())
                .build();

        RegistroHoras r2 = RegistroHoras.builder()
                .id(2L)
                .tarea(tarea2)
                .horasTrabajadas(3.5)
                .fechaRegistro(LocalDateTime.now())
                .build();

        when(registroHorasRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        // double total = proyectoService.getTotalHorasPorProyecto(proyecto.getId());

        verify(registroHorasRepository).findAll();
    }

}
