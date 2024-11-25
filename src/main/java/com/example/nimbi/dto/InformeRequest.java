package com.example.nimbi.dto;

import java.time.LocalDate;
import java.util.List;

public class InformeRequest {
    private List<String> organizaciones;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;

    // Getters y setters
    public List<String> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<String> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}