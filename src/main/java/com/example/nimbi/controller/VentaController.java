package com.example.nimbi.controller;

import com.example.nimbi.dto.DetalleCantidadProductoVentaDTO;
import com.example.nimbi.dto.VentaDTO;
import com.example.nimbi.model.Venta;
import com.example.nimbi.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Controlador de productos y detalle productos
 * @author Paula Uzcategui
 * @version 1.0
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    /**
     * Endpoint para crear una venta.
     *
     * @param detalleVentaDTOs Lista de productos vendidos con sus detalles (cantidad y detalleProductoId)
     * @param idCliente ID del cliente que realiza la compra
     * @return ResponseEntity con la venta creada y el estado HTTP adecuado
     */
    @PostMapping("/crear")
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody List<DetalleCantidadProductoVentaDTO> detalleVentaDTOs,
                                               @RequestParam Long idCliente) {
        try {
            // Llamar al servicio para crear la venta
            VentaDTO venta = ventaService.crearVenta(detalleVentaDTOs, idCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(venta); // Devolver la venta creada con estado 201
        } catch (IllegalArgumentException e) {
            // Manejar errores como "Cliente no encontrado" o "No hay suficientes productos"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Devolver 400 Bad Request si hay error en los datos
        }
    }

    // Endpoint to consult a sale by its ID
    @GetMapping("/{idVenta}")
    public ResponseEntity<VentaDTO> consultarVentaPorId(@PathVariable Long idVenta) {
        VentaDTO venta = ventaService.consultarVentaPorId(idVenta);

        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to consult sales within a date range
    @GetMapping("/consultarPorFecha")
    public ResponseEntity<List<VentaDTO>> consultarPorFecha(
            @RequestParam String fechaDesde,
            @RequestParam String fechaHasta) {

        try {
            LocalDate desde = convertStringToLocalDate(fechaDesde);
            LocalDate hasta = convertStringToLocalDate(fechaHasta);

            List<VentaDTO> ventas = ventaService.consultarVentasPorFecha(desde, hasta);
            return ResponseEntity.ok(ventas);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Consultar ventas por id del cliente
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<VentaDTO>> consultarPorIdCliente(
            @PathVariable Long idCliente){
        List<VentaDTO> ventas = ventaService.consultarVentasPorIdCliente(idCliente);
        return ResponseEntity.ok(ventas);
    }

    // Method to convert String to LocalDate
    private LocalDate convertStringToLocalDate(String dateString) {
        // Specify the date format you expect, e.g., "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}
