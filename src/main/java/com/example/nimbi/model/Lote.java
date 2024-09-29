package com.example.nimbi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lote") // Si la tabla en la DB se llama diferente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlote")
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne // un proveedor puede tener varios lotes
    @JoinColumn(name = "idproveedor", referencedColumnName = "idproveedor")
    private Proveedor proveedor;  // Llave foránea a la entidad Proveedor

    @Column(name = "total")
    private Double total;

    @Column(name = "cantidad")
    private Integer cantidad;


    // Relaciones

    @OneToMany(mappedBy = "lote") // Relación con producto
    private List<Producto> productos; // Lista de productos del lote
}