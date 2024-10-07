package com.example.nimbi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class VentaDTO {

    Long idVenta;
    LocalDate fecha;
    Long idCliente;
    String nombreCliente;
    Double total;

    List<DetalleCantidadProductoVentaDTO> detalleCantidadProductoVentaDTOList;

}
