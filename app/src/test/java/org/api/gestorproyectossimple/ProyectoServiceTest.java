package org.api.gestorproyectossimple;
import org.api.gestorproyectossimple.model.Proyecto;
import org.api.gestorproyectossimple.repository.ProyectoRepository;
import org.api.gestorproyectossimple.service.ProyectoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ProyectoServiceTest {

    private ProyectoRepository proyectoRepository;
    private ProyectoService proyectoService;

    @BeforeEach
    void setUp() {
        // Inicialización de Mockito (simulación del Repositorio)
        proyectoRepository = mock(ProyectoRepository.class);

        // Creación del Servicio, inyectando el Mock
        proyectoService = new ProyectoService(proyectoRepository);
    }


    @Test
    void testGuardarProyecto() {
        // ARRANGE
        Proyecto proyectoNuevo = Proyecto.builder().nombre("App Móvil").descripcion("Nueva app").build();
        Proyecto proyectoGuardado = Proyecto.builder().id(1L).nombre("App Móvil").descripcion("Nueva app").build();

        // Simular que el repositorio devuelve el objeto con ID
        when(proyectoRepository.save(proyectoNuevo)).thenReturn(proyectoGuardado);

        // ACT
        Proyecto resultado = proyectoService.guardar(proyectoNuevo);

        // ASSERT
        assertNotNull(resultado.getId());
        assertEquals("App Móvil", resultado.getNombre());
        // Verificar que el método save() del repositorio fue llamado
        verify(proyectoRepository, times(1)).save(proyectoNuevo);
    }


    @Test
    void testListarProyectos() {
        // ARRANGE
        List<Proyecto> lista = List.of(
                Proyecto.builder().id(1L).nombre("P1").build(),
                Proyecto.builder().id(2L).nombre("P2").build()
        );

        when(proyectoRepository.findAll()).thenReturn(lista);

        // ACT
        List<Proyecto> resultado = proyectoService.listarProyectos();

        // ASSERT
        assertEquals(2, resultado.size());
        assertEquals("P2", resultado.get(1).getNombre());
        verify(proyectoRepository, times(1)).findAll();
    }


    @Test
    void testBuscarPorIdEncontrado() {
        // ARRANGE
        Proyecto p = Proyecto.builder().id(5L).nombre("Proyecto Secreto").build();

        when(proyectoRepository.findById(5L)).thenReturn(Optional.of(p));

        // ACT
        Optional<Proyecto> resultado = proyectoService.buscarPorId(5L);

        // ASSERT
        assertTrue(resultado.isPresent());
        assertEquals("Proyecto Secreto", resultado.get().getNombre());
        verify(proyectoRepository, times(1)).findById(5L);
    }


    @Test
    void testBuscarPorIdNoEncontrado() {
        // ARRANGE
        when(proyectoRepository.findById(99L)).thenReturn(Optional.empty());

        // ACT
        Optional<Proyecto> resultado = proyectoService.buscarPorId(99L);

        // ASSERT
        assertFalse(resultado.isPresent());
        verify(proyectoRepository, times(1)).findById(99L);
    }


    @Test
    void testActualizarProyectoExistente() {
        // ARRANGE
        Long proyectoId = 3L;
        LocalDate fechaInicio = LocalDate.of(2024, 1, 1);

        Proyecto proyectoViejo = Proyecto.builder().id(proyectoId).nombre("Viejo").descripcion("d1").fechaInicio(fechaInicio).build();
        Proyecto proyectoUpdate = Proyecto.builder().nombre("Nuevo Nombre").descripcion("d2").fechaInicio(fechaInicio).fechaFin(LocalDate.of(2025, 1, 1)).build();

        // 1. Simular la búsqueda (debe encontrar el viejo)
        when(proyectoRepository.findById(proyectoId)).thenReturn(Optional.of(proyectoViejo));
        // 2. Simular el guardado (devuelve el objeto que se le pasa, ya actualizado)
        when(proyectoRepository.save(any(Proyecto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        Proyecto actualizado = proyectoService.actualizar(proyectoId, proyectoUpdate);

        // ASSERT
        assertEquals(proyectoId, actualizado.getId()); // Verifica que el ID se mantiene
        assertEquals("Nuevo Nombre", actualizado.getNombre());
        assertEquals("d2", actualizado.getDescripcion());
        assertEquals(LocalDate.of(2025, 1, 1), actualizado.getFechaFin());

        // Verificaciones
        verify(proyectoRepository, times(1)).findById(proyectoId);
        verify(proyectoRepository, times(1)).save(proyectoViejo);
    }


    @Test
    void testActualizarNoEncontrado_LanzaExcepcion() {
        // ARRANGE
        Long idInexistente = 99L;
        when(proyectoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // ACT & ASSERT
        Exception e = assertThrows(RuntimeException.class,
                () -> proyectoService.actualizar(idInexistente, new Proyecto()));

        assertEquals("Proyecto no encontrado", e.getMessage());

        // Verificamos que el save nunca se intentó
        verify(proyectoRepository, never()).save(any());
    }


    @Test
    void testEliminarProyecto() {
        Long proyectoId = 10L;

        // ARRANGE: Configura el Mock para que no haga nada (void method)
        doNothing().when(proyectoRepository).deleteById(proyectoId);

        // ACT
        proyectoService.eliminar(proyectoId);

        // ASSERT: Verifica que el método deleteById() del repositorio fue llamado
        verify(proyectoRepository, times(1)).deleteById(proyectoId);
    }


    @Test
    void testEliminarProyecto_IdNoExistente() {
        Long proyectoId = 99L;

        // Simular un comportamiento que no falla si el ID no existe
        doNothing().when(proyectoRepository).deleteById(proyectoId);

        // ACT (No debe lanzar excepción)
        assertDoesNotThrow(() -> proyectoService.eliminar(proyectoId));

        // ASSERT
        verify(proyectoRepository, times(1)).deleteById(proyectoId);
    }
}