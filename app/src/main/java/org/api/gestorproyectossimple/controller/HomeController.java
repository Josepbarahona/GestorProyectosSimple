package org.api.gestorproyectossimple.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controlador principal de bienvenida.
 * Devuelve HTML si accedes desde un navegador,
 * o JSON si accedes desde Postman / API.
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public Object home(HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");

        // Si detecta navegador, devuelve HTML
        if (userAgent != null && (userAgent.contains("Mozilla") || userAgent.contains("Chrome"))) {
            return """
                    <html>
                      <body style="font-family: Arial; text-align: center; margin-top: 80px;">
                        <h1> Gestor de Proyectos Simple API</h1>
                        <p> Estado: En ejecución correctamente</p>
                        <h3> Endpoints disponibles:</h3>
                        <ul style="list-style: none; padding: 0;">
                          <li> /api/proyectos</li>
                          <li> /api/tareas</li>
                          <li> /api/empleados</li>
                          <li> /api/registrohoras</li>
                        </ul>
                        <p style="margin-top: 40px; color: gray;">By Jose Wilfredo Ponce Barahona</p>
                      </body>
                    </html>
                    """;
        }

        // Si detecta Postman u otro cliente API, devuelve JSON
        return Map.of(
                "app", "Gestor de Proyectos Simple API",
                "status", "En ejecución correctamente",
                "endpoints", new String[]{
                        "/api/proyectos",
                        "/api/tareas",
                        "/api/empleados",
                        "/api/registrohoras"
                },
                "autor", "Jose Wilfredo Ponce Barahona"
        );
    }
}
