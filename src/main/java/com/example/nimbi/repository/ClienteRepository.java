package com.example.nimbi.repository;

import com.example.nimbi.model.Cliente;
import com.example.nimbi.model.DetalleProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    /*
    Buscar por nombre suggestion
     */
    @Query("SELECT c FROM Cliente c WHERE " +
            "upper( c.nombre) LIKE upper(CONCAT('%', :nombre, '%'))")
    Page<Cliente> findByNombre(@Param("nombre") String nombre,Pageable pageable);



}
