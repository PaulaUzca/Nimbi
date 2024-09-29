package com.example.nimbi.controller;

import com.example.nimbi.dto.DetalleProductoDTO;
import com.example.nimbi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador de productos y detalle productos
 * @author Paula Uzcategui
 * @version 1.0
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Obtener una lista de DetalleProductoDTO con los productos disponibles agrupados.
     *
     * @return Lista de DetalleProductoDTO
     */
    @GetMapping("/detalles")
    public ResponseEntity<List<DetalleProductoDTO>> obtenerProductosDisponiblesAgrupados() {
        List<DetalleProductoDTO> detalles = productoService.obtenerDetallesProductosDistintosDisponibles();
        return ResponseEntity.ok(detalles); // Retornar 200 OK con la lista
    }

    /**
     * Obtener un DetalleProductoDTO por el id del detalle producto.
     *
     * @param idDetalleProducto ID del detalle producto
     * @return DetalleProductoDTO
     */
    @GetMapping("/detalle/{idDetalleProducto}")
    public ResponseEntity<DetalleProductoDTO> obtenerDetalleProducto(@PathVariable Long idDetalleProducto) {
        DetalleProductoDTO detalle = productoService.obtenerCantidadProductos(idDetalleProducto);
        return detalle != null ? ResponseEntity.ok(detalle) : ResponseEntity.notFound().build(); // Retornar 404 si no se encuentra
    }

    /**
     * Buscar DetalleProductoDTO por nombre con sugerencias.
     *
     * @param nombre Nombre a buscar
     * @return Lista de DetalleProductoDTO
     */
    @GetMapping("/detalle/suggestion/{nombre}")
    public ResponseEntity<List<DetalleProductoDTO>> obtenerDetalleProducto(@PathVariable String nombre) {
        List<DetalleProductoDTO> detalles = productoService.buscarDetalleProductoPorNombreYDisponibilidad(nombre);
        return ResponseEntity.ok(detalles); // Retornar 200 OK con la lista
    }
}
