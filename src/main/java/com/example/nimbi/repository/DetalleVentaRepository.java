package com.example.nimbi.repository;

import com.example.nimbi.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    /**
     * Contar el número de DetalleVenta por idVenta y idProducto.
     *
     * @param idVenta ID de la venta
     * @param idProducto ID del producto
     * @return El conteo de DetalleVenta
     */
    @Query("SELECT COUNT(dv) FROM DetalleVenta dv WHERE dv.venta.id = :idVenta AND dv.producto.id = :idProducto")
    Integer countByVentaIdAndProductoId(@Param("idVenta") Long idVenta, @Param("idProducto") Long idProducto);

}
