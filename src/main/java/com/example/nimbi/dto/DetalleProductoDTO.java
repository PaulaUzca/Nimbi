package com.example.nimbi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetalleProductoDTO {
    private Long idDetalleProducto;
    private String nombre; // Nombre del detalle del producto
    private Integer cantidad; // Cantidad de productos disponibles
}