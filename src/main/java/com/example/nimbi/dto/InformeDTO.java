package com.example.nimbi.dto;

import java.time.LocalDate;

public class InformeDTO {
    private LocalDate fecha;
    private String nombreProducto;
    private Integer productosVendidos;
    private Double totalVenta;
    private int ventasTotales;
    private Double utilidad;

    // Constructor
    public InformeDTO(LocalDate fecha, int productosVendidos, double totalVenta, int ventasTotales, double utilidad) {
        this.fecha = fecha;
        this.productosVendidos = productosVendidos;
        this.totalVenta = totalVenta;
        this.ventasTotales = ventasTotales;
        this.utilidad = utilidad;
    }

    // Getters y setters
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(Integer productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(int ventasTotales) {
        this.ventasTotales = ventasTotales;
    }

    public Double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Double utilidad) {
        this.utilidad = utilidad;
    }
}
