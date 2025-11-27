package org.api.gestorproyectossimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del proyecto Gestor de Proyectos Simple.
 *
 * Esta clase es el punto de entrada de la aplicación Spring Boot.
 * La anotación @SpringBootApplication habilita varias cosas importantes:
 *  - @Configuration → permite registrar beans de configuración.
 *  - @EnableAutoConfiguration → configura Spring Boot automaticamente.
 *  - @ComponentScan → busca componentes (Controllers, Services, Repositories)
 *    dentro del paquete base org.api.gestorproyectossimple.
 *
 * Para ejecutar la app:
 *  - Desde IntelliJ: clic derecho → "Run 'GestorProyectosSimpleApplication'"
 */
@SpringBootApplication
public class GestorProyectosSimpleApplication {

    public static void main(String[] args) {
        // Este metodo arranca toda la aplicación Spring Boot.
        SpringApplication.run(GestorProyectosSimpleApplication.class, args);

        System.out.println(" Servidor iniciado correctamente en http://localhost:8080");
        System.out.println(" API Gestor de Proyectos Simple ejecutandose...");
    }
}
