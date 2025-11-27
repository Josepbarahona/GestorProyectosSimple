package org.api.gestorproyectossimple;

import org.api.gestorproyectossimple.service.EmpleadoService;
import org.api.gestorproyectossimple.model.Empleado;
import org.api.gestorproyectossimple.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Pruebas unitarias para EmpleadoService.
 * Se usa Mockito para simular el comportamiento del repositorio.
 */
@SpringBootTest
class EmpleadoServiceTest {

    private EmpleadoRepository empleadoRepository;
    private EmpleadoService empleadoService;

    // Se ejecuta antes de cada prueba
    @BeforeEach
    void setUp() {
        // Mock del repositorio (no toca la base de datos)
        empleadoRepository = mock(EmpleadoRepository.class);

        // Inyectamos el mock en el servicio
        empleadoService = new EmpleadoService(empleadoRepository);
    }
    /**
     * Prueba: guardar un nuevo empleado correctamente.
     */
    @Test
    void testGuardarEmpleado() {
        Empleado emp = new Empleado(1L, "Jose", "Desarrollador", "jose@gmail.com", 700.0);

        // Simulamos la respuesta del repositorio
        when(empleadoRepository.save(emp)).thenReturn(emp);

        // Ejecutamos la operación real
        Empleado resultado = empleadoService.guardar(emp);

        // Verificación
        assertNotNull(resultado);
        assertEquals("Jose", resultado.getNombre());
        verify(empleadoRepository, times(1)).save(emp);
    }
    /**
     * Prueba: listar empleados.
     */
    @Test
    void testListarEmpleados() {
        List<Empleado> lista = List.of(
                new Empleado(1L, "Ana", "Admin", "ana@mail.com", 500),
                new Empleado(2L, "Jose", "Dev", "jose@mail.com", 700)
        );

        when(empleadoRepository.findAll()).thenReturn(lista);

        List<Empleado> resultado = empleadoService.listarEmpleados();

        assertEquals(2, resultado.size());
        verify(empleadoRepository, times(1)).findAll();
    }
    /**
     * Prueba: buscar un empleado que sí existe.
     */
    @Test
    void testBuscarPorIdEncontrado() {
        Empleado emp = new Empleado(5L, "Luis", "QA", "luis@mail.com", 600);

        when(empleadoRepository.findById(5L)).thenReturn(Optional.of(emp));

        Optional<Empleado> resultado = empleadoService.buscarPorId(5L);

        assertTrue(resultado.isPresent());
        assertEquals("Luis", resultado.get().getNombre());
    }
    /**
     * Prueba: actualizar un empleado correctamente.
     */
    @Test
    void testActualizarEmpleado() {
        Empleado viejo = new Empleado(3L, "Mario", "Dev", "mario@mail.com", 500);
        Empleado nuevo = new Empleado(null, "Mario Update", "Senior Dev", "nuevo@mail.com", 900);

        when(empleadoRepository.findById(3L)).thenReturn(Optional.of(viejo));
        when(empleadoRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Empleado actualizado = empleadoService.actualizar(3L, nuevo);

        assertEquals("Mario Update", actualizado.getNombre());
        assertEquals("Senior Dev", actualizado.getCargo());
        assertEquals("nuevo@mail.com", actualizado.getEmail());
        assertEquals(900, actualizado.getSalario());
    }
    /**
     * Prueba: actualizar un empleado no existente (debe lanzar excepción).
     */
    @Test
    void testActualizarNoEncontrado() {
        when(empleadoRepository.findById(99L)).thenReturn(Optional.empty());

        Exception e = assertThrows(RuntimeException.class,
                () -> empleadoService.actualizar(99L, new Empleado()));

        assertEquals("Empleado no encontrado", e.getMessage());
    }

    /**
     * Prueba: eliminar un empleado por ID.
     */
    @Test
    void testEliminarEmpleado() {
        doNothing().when(empleadoRepository).deleteById(10L);

        empleadoService.eliminar(10L);

        verify(empleadoRepository, times(1)).deleteById(10L);
    }
}
