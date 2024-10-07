package com.example.nimbi.mapper;

import com.example.nimbi.dto.DetalleCantidadProductoVentaDTO;
import com.example.nimbi.model.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    @Mapping(source = "detalleVenta.id", target = "idDetalleProducto")
    @Mapping(source = "detalleVenta.producto.detalleProducto.nombre", target = "nombreProducto")
    @Mapping(target="cantidad", ignore = true)
    @Mapping(source = "precioUnidad", target = "precioUnidad")
    DetalleCantidadProductoVentaDTO toDetalleCantidadProductoVentaDTO(DetalleVenta detalleVenta);

}
