package com.example.nimbi.repository;

import com.example.nimbi.model.Cliente;
import com.example.nimbi.model.DetalleProducto;
import com.example.nimbi.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);

    List<Venta> findAllByCliente(Cliente cliente);

    /*
    Buscar venta por nombre del cliente
    */
    @Query("SELECT v FROM Venta v WHERE " +
            "(:nombreCliente IS NULL OR :nombreCliente = '' OR " +
            "upper(v.cliente.nombre) LIKE upper(CONCAT('%', :nombreCliente, '%'))) " +
            "ORDER BY v.cliente.nombre ASC")
    Page<Venta> findVentaByNombreCliente(@Param("nombreCliente") String nombreCliente, Pageable pageable );

}
