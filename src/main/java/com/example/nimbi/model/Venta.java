package com.example.nimbi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventa")
    private Long id;

    private LocalDate fecha;

    @ManyToOne // un lote puede tener muchos productos
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private Cliente cliente;

    private Double total;


    // relaciones
    @OneToMany(mappedBy = "venta") // Relación con detalleVenta
    private List<DetalleVenta> detallesVenta; // Lista de detalleVenta asociado a la venta
}
