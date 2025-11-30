package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.Empleado;
import org.api.gestorproyectossimple.model.Proyecto;
import org.api.gestorproyectossimple.model.Tarea;
import org.api.gestorproyectossimple.model.RegistroHoras;
import org.api.gestorproyectossimple.repository.TareaRepository;
import org.api.gestorproyectossimple.repository.RegistroHorasRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private RegistroHorasRepository registroHorasRepository;

    @InjectMocks
    private TareaService tareaService;


    @Test
    void testGuardarTarea() {
        Tarea tarea = Tarea.builder()
                .titulo("Implementación API")
                .descripcion("Crear endpoints")
                .fechaLimite(LocalDate.now())
                .build();

        when(tareaRepository.save(tarea)).thenReturn(tarea);

        Tarea result = tareaService.guardar(tarea);

        assertNotNull(result);
        assertEquals("Implementación API", result.getTitulo());
        verify(tareaRepository).save(tarea);
    }


    @Test
    void testObtenerTareaPorId() {
        Tarea tarea = Tarea.builder()
                .id(5L)
                .titulo("Crear servicio")
                .build();

        when(tareaRepository.findById(5L)).thenReturn(Optional.of(tarea));

        Optional<Tarea> result = tareaService.buscarPorId(5L);

        assertNotNull(result);
        assertEquals("Crear servicio", result.get().getTitulo());
        verify(tareaRepository).findById(5L);
    }


    @Test
    void testListarTareas() {
        Tarea t1 = new Tarea();
        Tarea t2 = new Tarea();

        when(tareaRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Tarea> result = tareaService.listarTareas();

        assertEquals(2, result.size());
        verify(tareaRepository).findAll();
    }


    @Test
    void testCalcularHorasPorTarea() {

        RegistroHoras r1 = RegistroHoras.builder()
                .id(1L)
                .horasTrabajadas(2.0)
                .fechaRegistro(LocalDateTime.now())
                .build();

        RegistroHoras r2 = RegistroHoras.builder()
                .id(2L)
                .horasTrabajadas(4.0)
                .fechaRegistro(LocalDateTime.now())
                .build();

        when(registroHorasRepository.findByTareaId(50L))
                .thenReturn(Arrays.asList(r1, r2));

        // double total = tareaService.getTotalHorasPorTarea(50L);

        // assertEquals(6.0, total);
        verify(registroHorasRepository).findByTareaId(50L);
    }

}
