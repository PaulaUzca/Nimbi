package com.example.nimbi.mapper;

import com.example.nimbi.dto.VentaDTO;
import com.example.nimbi.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    @Mapping(source = "venta.id", target = "idVenta")
    @Mapping(source = "venta.cliente.id", target ="idCliente")
    @Mapping(source = "venta.cliente.nombre", target = "nombreCliente")
    @Mapping(source = "venta.fecha", target = "fecha") // Map fecha
    @Mapping(source = "venta.total", target = "total") // Map total
    @Mapping(target = "detalleCantidadProductoVentaDTOList", ignore = true) // Map DetalleVentas to DTO list
    VentaDTO toVentaDTO(Venta venta);
}
