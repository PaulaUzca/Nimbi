package com.example.nimbi.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data // Genera getters, setters, toString, equals, hashCode, etc.
@NoArgsConstructor // Genera un constructor vacío
@AllArgsConstructor // Genera un constructor con todos los campos
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Long id;

    @Column(name = "estado")
    private String estado; // Estado del producto (puede ser "activo", "inactivo", etc.)

    @ManyToOne // un lote puede tener muchos productos
    @JoinColumn(name = "idlote", referencedColumnName = "idlote")
    private Lote lote; // Llave foránea a la entidad Lote

    @ManyToOne // un detalle producto puede tener muchos productos
    @JoinColumn(name = "iddetalleproducto", referencedColumnName ="iddetalleproducto")
    private DetalleProducto detalleProducto;

}
