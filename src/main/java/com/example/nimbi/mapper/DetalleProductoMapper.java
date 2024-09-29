package com.example.nimbi.mapper;

import com.example.nimbi.dto.DetalleProductoDTO;
import com.example.nimbi.model.DetalleProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring") // Esto permite que Spring gestione el mapper
public interface DetalleProductoMapper {

    @Mapping(source = "detalleProducto.nombre", target = "nombre")
    @Mapping(source = "detalleProducto.id", target = "idDetalleProducto")
    @Mapping(source = "cantidad", target = "cantidad")
    DetalleProductoDTO toDTO(DetalleProducto detalleProducto, Integer cantidad);

}