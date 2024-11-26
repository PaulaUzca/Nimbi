package com.example.nimbi.service;

import com.example.nimbi.constantes.Constantes;
import com.example.nimbi.dto.DetalleCantidadProductoVentaDTO;
import com.example.nimbi.dto.VentaDTO;
import com.example.nimbi.mapper.DetalleVentaMapper;
import com.example.nimbi.mapper.VentaMapper;
import com.example.nimbi.model.*;
import com.example.nimbi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    private VentaMapper ventaMapper;

    @Autowired
    private DetalleVentaMapper detalleVentaMapper;

    /**
     * Crear una venta con los detalles de productos vendidos
     *
     * @param detalleVentaDTOs Lista de productos vendidos con sus detalles
     * @param idCliente        ID del cliente que realiza la compra
     * @return Venta creada
     */
    @Transactional
    public VentaDTO crearVenta(List<DetalleCantidadProductoVentaDTO> detalleVentaDTOs, Long idCliente) {
        // Buscar cliente
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Crear la venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDate.now());
        venta.setTotal(calcularTotal(detalleVentaDTOs)); // Calculamos el total
        ventaRepository.save(venta); // Guardamos la venta

        // Procesar cada detalle de la venta
        detalleVentaDTOs.stream().forEach(dto -> procesarDetalleVenta(dto, venta));

        return ventaMapper.toVentaDTO(venta);
    }


    /**
     * Consultar una venta por su ID.
     *
     * @param idVenta ID de la venta a consultar
     * @return Venta
     */
    public VentaDTO consultarVentaPorId(Long idVenta) {
        Venta venta =  ventaRepository.findById(idVenta).orElseThrow(() -> new NoSuchElementException());
        return ventaDTOCompleto(venta);
    }


    /**
     * Consultar todas las ventas dentro de un rango de fechas.
     *
     * @param fechaDesde fecha de inicio
     * @param fechaHasta fecha de fin
     * @return List<Venta> lista de ventas
     */
    public List<VentaDTO> consultarVentasPorFecha(LocalDate fechaDesde, LocalDate fechaHasta) {
        List<Venta> ventas = ventaRepository.findByFechaBetween(fechaDesde, fechaHasta);
        return ventas.isEmpty() ? Collections.emptyList() : ventas.stream()
                .map(this::ventaDTOCompleto)
                .collect(Collectors.toList());
    }

    /**
     * Consultar todas las ventas de un cliente
     *
     * @param idCliente long
     * @return List<Venta> lista de ventas
     */
    public List<VentaDTO> consultarVentasPorIdCliente(Long idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        List<Venta> ventas = ventaRepository.findAllByCliente(cliente);
        return ventas.isEmpty() ? Collections.emptyList() : ventas.stream()
                .map(this::ventaDTOCompleto)
                .collect(Collectors.toList());
    }



    // metodos privados

    /**
     * Procesar cada detalle de la venta
     *
     * @param dto   DetalleVentaDTO con información de detalleProducto y cantidad
     * @param venta La venta a la que pertenece el detalle
     * @return DetalleVenta creado
     */
    private void procesarDetalleVenta(DetalleCantidadProductoVentaDTO dto, Venta venta) {
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
            // TODO de pronto seria bueno ponerle el precio al producto desde detalle venta en la base de datos
            detalleVenta.setPrecioUnidad(1.0);

            detalleVentaRepository.save(detalleVenta); // Guardamos cada detalle de venta
        });

    }

    /** Calcular total asumiento que todos los productos valor en 1.0$
     * @param detalleCantidadProductoVentaDTOList
     * @return
     */
    private double calcularTotal(List<DetalleCantidadProductoVentaDTO> detalleCantidadProductoVentaDTOList) {
        return detalleCantidadProductoVentaDTOList.stream()
                .mapToDouble(detalle -> detalle.getCantidad() * 1.0) // Multiply cantidad by 1.0 (default unit value)
                .sum(); // Sum all values
    }


    private VentaDTO ventaDTOCompleto(Venta venta){
        List<DetalleCantidadProductoVentaDTO> detalleCantidadProductoVentaDTOList = venta.getDetallesVenta().stream().map(
                detalleVenta -> {
                    DetalleCantidadProductoVentaDTO detalleCantidadVenta = detalleVentaMapper.toDetalleCantidadProductoVentaDTO(detalleVenta);
                    // contar cantidad de productos con mismo idVenta
                    detalleCantidadVenta.setCantidad(
                            detalleVentaRepository.countByVentaIdAndProductoId(detalleVenta.getVenta().getId(), detalleVenta.getProducto().getId()));
                    return detalleCantidadVenta;
                }
        ).collect(Collectors.toList());
        VentaDTO ventaDTO = ventaMapper.toVentaDTO(venta);
        ventaDTO.setDetalleCantidadProductoVentaDTOList(detalleCantidadProductoVentaDTOList);
        return ventaDTO;
    }
}
