package org.api.gestorproyectossimple.service;

import org.api.gestorproyectossimple.model.Empleado;
import org.api.gestorproyectossimple.repository.EmpleadoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;


    @Test
    void testGuardarEmpleado() {

        Empleado empleado = Empleado.builder()
                .nombre("Juan Pérez")
                .cargo("Desarrollador")
                .email("juan@example.com")
                .salario(1200)
                .build();

        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado result = empleadoService.guardar(empleado);

        assertNotNull(result);
        assertEquals("Juan Pérez", result.getNombre());
        assertEquals("juan@example.com", result.getEmail());
        verify(empleadoRepository).save(empleado);
    }


    @Test
    void testObtenerEmpleadoPorId() {
        Empleado empleado = Empleado.builder()
                .id(10L)
                .nombre("Carlos Mendoza")
                .cargo("QA Tester")
                .email("carlos@example.com")
                .build();

        when(empleadoRepository.findById(10L)).thenReturn(Optional.of(empleado));

        Optional<Empleado> result = empleadoService.buscarPorId(10L);

        assertTrue(result.isPresent());
        assertEquals("Carlos Mendoza", result.get().getNombre());
        verify(empleadoRepository).findById(10L);
    }


    @Test
    void testListarEmpleados() {
        Empleado e1 = new Empleado();
        Empleado e2 = new Empleado();

        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Empleado> lista = empleadoService.listarEmpleados();

        assertEquals(2, lista.size());
        verify(empleadoRepository).findAll();
    }

}
