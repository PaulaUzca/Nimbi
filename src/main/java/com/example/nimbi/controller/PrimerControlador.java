/**
 * Controlador de ejemplo de la aplicación.
 * Maneja las solicitudes relacionadas con el saludo.
 *
 * @author Paula Uzcátegui
 * @version 1.0
 */


package com.example.nimbi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Marca la clase como un controlador REST
@RequestMapping("/api")  // Prefijo para las rutas
public class PrimerControlador {

    @GetMapping("/saludo")  // Mapea las solicitudes GET a /api/saludo
    public String saludo() {
        return "¡Hola, Spring Boot!";
    }

}
