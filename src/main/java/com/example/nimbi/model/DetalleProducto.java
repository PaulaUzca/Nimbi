package com.example.nimbi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "detalleproducto")
@Data //
@NoArgsConstructor
@AllArgsConstructor
public class DetalleProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalleproducto")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria")
    private String categoria;

    //relaciones
    @OneToMany(mappedBy = "detalleProducto") // Relación con producto
    private List<Producto> productos; // Lista de lotes asociados al proveedor

}
