package com.example.nimbi.service;

import com.example.nimbi.constantes.Constantes;
import com.example.nimbi.dto.DetalleProductoDTO;
import com.example.nimbi.mapper.DetalleProductoMapper;
import com.example.nimbi.model.DetalleProducto;
import com.example.nimbi.repository.DetalleProductoRepository;
import com.example.nimbi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Servicio de productos y detalle productos
 * @author Paula Uzcategui
 * @version 1.0
 */

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleProductoRepository detalleProductoRepository;

    @Autowired
    private DetalleProductoMapper detalleProductoMapper; // Inyectar el mapper


    /**
     * Método para obtener los nombres de productos que están disponibles al momento
     *
     * @return Lista de DetalleProductoDTO
     */
    public List<DetalleProductoDTO> obtenerDetallesProductosDistintosDisponibles() {
        List<DetalleProducto> detalleProductoList = detalleProductoRepository.findDetalleProductoByEstado(Constantes.ESTADO_DISPONIBLE);

        return detalleProductoList.stream()
                .map(detalleProducto -> detalleProductoMapper.toDTO(detalleProducto, null)) // Mapeamos a DTO
                .collect(Collectors.toList()); // Recopilamos en una lista
    }

    /**
     * Metodo para obtener la cantidad de productos disponibles según un detalle producto
     *
     * @param idDetalleProducto ID del detalle producto
     * @return DetalleProductoDTO con la cantidad de productos disponibles
     */
    public DetalleProductoDTO obtenerCantidadProductos(Long idDetalleProducto) {
        DetalleProducto detalleProducto = obtenerDetalleProductoPorId(idDetalleProducto);
        if (detalleProducto == null) {
            return null; // Aquí podría lanzar una excepción en lugar de retornar null
        }

        Long count = productoRepository.countByDetalleProductoAndEstado(detalleProducto, Constantes.ESTADO_DISPONIBLE);
        return detalleProductoMapper.toDTO(detalleProducto, count.intValue());
    }


    /**
     * Encontrar detalle producto por sugerencia de nombre.
     *
     * @param nombre Nombre a buscar
     * @return Lista de DetalleProductoDTO con los productos disponibles
     */
    public List<DetalleProductoDTO> buscarDetalleProductoPorNombreYDisponibilidad(String nombre) {
        Pageable pageable = PageRequest.of(0, 20); // Página 0, tamaño 20
        Page<DetalleProducto> page = detalleProductoRepository.findByNombreLikeAndProductoDisponible(nombre, Constantes.ESTADO_DISPONIBLE, pageable);

        return page.getContent().stream()
                .map(detalleProducto -> {
                    // Contar directamente en lugar de obtener toda la lista
                    Long count = productoRepository.countByDetalleProductoAndEstado(detalleProducto, Constantes.ESTADO_DISPONIBLE);
                    return detalleProductoMapper.toDTO(detalleProducto, count.intValue());
                }) // Mapear a DTO
                .collect(Collectors.toList());
    }

    /**
     * Encontrar detalle producto por ID.
     *
     * @param idDetalle ID del detalle producto
     * @return DetalleProducto
     */
    private DetalleProducto obtenerDetalleProductoPorId(Long idDetalle) {
        return detalleProductoRepository.findById(idDetalle).orElse(null);
    }
}
