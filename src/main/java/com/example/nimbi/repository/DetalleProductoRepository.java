package com.example.nimbi.repository;

import com.example.nimbi.model.DetalleProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repositorio de detalle productos
 * @author Paula Uzcategui
 * @version 1.0
 */

@Repository
public interface DetalleProductoRepository extends JpaRepository<DetalleProducto, Long> {

    /*
    Buscar por nombre suggestion
     */
    @Query("SELECT d FROM DetalleProducto d WHERE " +
            "upper( d.nombre) LIKE upper(CONCAT('%', :nombre, '%')) AND " +
            "EXISTS (SELECT p FROM Producto p WHERE p.detalleProducto.id = d.id AND p.estado = :estado)")
    Page<DetalleProducto> findByNombreLikeAndProductoDisponible(@Param("nombre") String nombre,
                                                                @Param("estado") String estado,
                                                                Pageable pageable);


    /*
    Buscar detalle producto que tenga cierto estado
    */
    @Query("SELECT d FROM DetalleProducto d WHERE " +
            "EXISTS (SELECT p FROM Producto p WHERE p.detalleProducto.id = d.id AND p.estado = :estado)")
    List<DetalleProducto> findDetalleProductoByEstado( @Param("estado") String estado);

}
