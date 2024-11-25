package com.example.nimbi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nimbi.dto.InformeDTO;
import com.example.nimbi.model.Venta;
import com.example.nimbi.repository.VentaRepository;

@Service
public class InformeService {

    private final VentaRepository ventaRepository;

    @Autowired
    public InformeService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<InformeDTO> generarInforme(List<String> organizaciones, LocalDate fechaInicial, LocalDate fechaFinal) {
        if (organizaciones.contains("Organización A")) {
            List<Venta> ventas = ventaRepository.findByFechaBetween(fechaInicial, fechaFinal);
            return ventas.stream().map(venta -> {
                InformeDTO informe = new InformeDTO();
                informe.setFecha(venta.getFecha());
                informe.setTotalVenta(venta.getTotal());
                informe.setProductosVendidos(venta.getDetallesVenta().size());
                informe.setVentasTotales(ventas.size());

                return informe;
            }).collect(Collectors.toList());
        }

        return List.of();
    }
}
