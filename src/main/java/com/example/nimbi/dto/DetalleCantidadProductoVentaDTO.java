package com.example.nimbi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetalleCantidadProductoVentaDTO {
    private Long idDetalleProducto; // ID del detalle del producto
    private Integer cantidad; // Cantidad de productos que se están comprando
}