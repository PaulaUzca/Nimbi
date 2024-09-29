package com.example.nimbi.service;

import com.example.nimbi.constantes.Constantes;
import com.example.nimbi.dto.DetalleCantidadProductoVentaDTO;
import com.example.nimbi.model.*;
import com.example.nimbi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleProductoRepository detalleProductoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Crear una venta con los detalles de productos vendidos
     *
     * @param detalleVentaDTOs Lista de productos vendidos con sus detalles
     * @param idCliente        ID del cliente que realiza la compra
     * @return Venta creada
     */
    @Transactional
    public Venta crearVenta(List<DetalleCantidadProductoVentaDTO> detalleVentaDTOs, Long idCliente) {
        // Buscar cliente
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Crear la venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDate.now());
        venta.setTotal(calcularTotal()); // Calculamos el total
        ventaRepository.save(venta); // Guardamos la venta

        // Procesar cada detalle de la venta
        List<DetalleVenta> detallesVenta = detalleVentaDTOs.stream()
                .map(dto -> procesarDetalleVenta(dto, venta))
                .collect(Collectors.toList());

        // Guardar los detalles de la venta
        detalleVentaRepository.saveAll(detallesVenta);

        return venta;
    }

    /**
     * Procesar cada detalle de la venta
     *
     * @param dto   DetalleVentaDTO con información de detalleProducto y cantidad
     * @param venta La venta a la que pertenece el detalle
     * @return DetalleVenta creado
     */
    private DetalleVenta procesarDetalleVenta(DetalleCantidadProductoVentaDTO dto, Venta venta) {
        // Buscar el detalleProducto
        DetalleProducto detalleProducto = detalleProductoRepository.findById(dto.getIdDetalleProducto())
                .orElseThrow(() -> new IllegalArgumentException("DetalleProducto no encontrado"));

        // Buscar productos disponibles con el detalleProducto
        List<Producto> productosDisponibles = productoRepository.findByDetalleProductoAndEstado(detalleProducto, Constantes.ESTADO_DISPONIBLE);

        // Verificar que haya suficientes productos disponibles
        if (productosDisponibles.size() < dto.getCantidad()) {
            throw new IllegalArgumentException("No hay suficientes productos disponibles para el detalleProducto con ID: " + dto.getIdDetalleProducto());
        }

        // Seleccionar la cantidad solicitada de productos disponibles
        List<Producto> productosParaVenta = productosDisponibles.subList(0, dto.getCantidad());

        // Cambiar el estado de los productos a "VENDIDO" y guardar
        productosParaVenta.forEach(producto -> {
            producto.setEstado(Constantes.ESTADO_VENDIDO);
            productoRepository.save(producto); // Guardamos cada producto actualizado
        });

        // Crear los detalles de venta para cada producto vendido
        productosParaVenta.forEach(producto -> {
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setVenta(venta);
            detalleVenta.setProducto(producto);
            detalleVenta.setPrecioUnidad(generarNumeroAleatorioDecimal());

            detalleVentaRepository.save(detalleVenta); // Guardamos cada detalle de venta
        });

        return null; // Retornar null o modificar si es necesario
    }

    /**
     * Calcular el total de la venta a partir de la lista de detalles
     * Se hace random
     *
     * @return Total de la venta
     */
    private Double calcularTotal() {
        return generarNumeroAleatorioDecimal();
    }

    public double generarNumeroAleatorioDecimal() {
        double min = 200000;
        double max = 10000000;
        Random random = new Random();
        // Generates a random double between min (inclusive) and max (exclusive)
        return min + (max - min) * random.nextDouble();
    }
}
