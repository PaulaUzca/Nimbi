package com.example.nimbi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<String> obtenerOrganizaciones() {
        // Lógica para obtener las organizaciones según las bases de datos existentes.
        return List.of("Organización A", "Organización B"); // Cambiar esto por la lógica real.
    }

    public List<InformeDTO> generarInforme(List<String> organizaciones, LocalDate fechaInicial, LocalDate fechaFinal) {
        if (!organizaciones.isEmpty()) {
            List<Venta> ventas = ventaRepository.findByFechaBetween(fechaInicial, fechaFinal);
            return ventas.stream().collect(Collectors.groupingBy(Venta::getFecha))
                    .entrySet().stream()
                    .map(entry -> {
                        LocalDate fecha = entry.getKey();
                        List<Venta> ventasPorDia = entry.getValue();

                        int productosVendidos = ventasPorDia.stream()
                                .mapToInt(venta -> venta.getDetallesVenta().size())
                                .sum();

                        double totalVenta = ventasPorDia.stream()
                                .mapToDouble(Venta::getTotal)
                                .sum();

                        int ventasTotales = ventasPorDia.size();

                        double utilidad = totalVenta * 0.20;

                        return new InformeDTO(fecha, productosVendidos, totalVenta, ventasTotales, utilidad);
                    }).collect(Collectors.toList());
        }
        return List.of();
    }
}