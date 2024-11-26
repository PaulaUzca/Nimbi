package com.example.nimbi.repository;

import com.example.nimbi.model.Cliente;
import com.example.nimbi.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);

    List<Venta> findAllByCliente(Cliente cliente);
}
