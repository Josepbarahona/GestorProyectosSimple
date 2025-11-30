package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.Empleado;
import org.api.gestorproyectossimple.model.Tarea;
import org.api.gestorproyectossimple.model.RegistroHoras;
import org.api.gestorproyectossimple.repository.RegistroHorasRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistroHorasServiceTest {

    @Mock
    private RegistroHorasRepository registroHorasRepository;

    @InjectMocks
    private RegistroHorasService registroHorasService;


    @Test
    void testRegistrarHoras() {
        Empleado empleado = Empleado.builder()
                .id(1L)
                .nombre("Juan Pérez")
                .cargo("Backend Developer")
                .email("juan@example.com")
                .salario(1500)
                .build();

        Tarea tarea = Tarea.builder()
                .id(5L)
                .titulo("Implementar API")
                .build();

        RegistroHoras registro = RegistroHoras.builder()
                .horasTrabajadas(4.5)
                .fechaRegistro(LocalDateTime.now())
                .empleado(empleado)
                .tarea(tarea)
                .build();

        when(registroHorasRepository.save(registro)).thenReturn(registro);

        RegistroHoras result = registroHorasService.registrarHoras(registro);

        assertNotNull(result);
        assertEquals(4.5, result.getHorasTrabajadas());
        verify(registroHorasRepository).save(registro);
    }


    @Test
    void testObtenerHorasPorTarea() {
        RegistroHoras r1 = RegistroHoras.builder()
                .horasTrabajadas(2.0)
                .fechaRegistro(LocalDateTime.now())
                .build();

        RegistroHoras r2 = RegistroHoras.builder()
                .horasTrabajadas(3.0)
                .fechaRegistro(LocalDateTime.now())
                .build();

        when(registroHorasRepository.findByTareaId(100L))
                .thenReturn(Arrays.asList(r1, r2));

        double total = registroHorasService.getHorasPorTarea(100L);

        assertEquals(5.0, total);
        verify(registroHorasRepository).findByTareaId(100L);
    }


    @Test
    void testObtenerHorasPorEmpleado() {
        RegistroHoras r1 = RegistroHoras.builder()
                .horasTrabajadas(3.5)
                .fechaRegistro(LocalDateTime.now())
                .build();

        RegistroHoras r2 = RegistroHoras.builder()
                .horasTrabajadas(4.0)
                .fechaRegistro(LocalDateTime.now())
                .build();

        when(registroHorasRepository.findByEmpleadoId(1L))
                .thenReturn(Arrays.asList(r1, r2));

        List<RegistroHoras> result = registroHorasService.getHorasPorEmpleado(1L);

        assertEquals(2, result.size());
        assertEquals(3.5, result.get(0).getHorasTrabajadas());
        verify(registroHorasRepository).findByEmpleadoId(1L);
    }

}
