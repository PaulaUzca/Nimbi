package com.example.nimbi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalleventa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalleventa")
    private Long id;

    @ManyToOne // una venta tiene muchos detalle venta
    @JoinColumn(name = "idventa", referencedColumnName = "idventa")
    private Venta venta;

    @OneToOne // un producto solo se vende una vez
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    private Producto producto;

    @Column(name = "preciounidad")
    private Double precioUnidad;
}
