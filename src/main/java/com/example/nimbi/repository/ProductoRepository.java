package com.example.nimbi.repository;

import com.example.nimbi.model.DetalleProducto;
import com.example.nimbi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de productos
 * @author Paula Uzcategui
 * @version 1.0
 */

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByEstado(String estado);

    List<Producto> findByDetalleProducto(DetalleProducto detalleProducto);

    Long countByDetalleProductoAndEstado( DetalleProducto detalleProducto, String estado);

    List<Producto> findByDetalleProductoAndEstado(DetalleProducto detalleProducto, String estado);

}
