package com.example.nimbi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nimbi.dto.InformeDTO;
import com.example.nimbi.dto.InformeRequest;
import com.example.nimbi.service.InformeService;

@RestController
@RequestMapping("/api/informes")
@CrossOrigin(origins = "http://localhost:4200")
public class InformeController {

    private final InformeService informeService;

    @Autowired
    public InformeController(InformeService informeService) {
        this.informeService = informeService;
    }

    @PostMapping("/generar-informe")
    public ResponseEntity<List<InformeDTO>> generarInforme(@RequestBody InformeRequest request) {
        List<InformeDTO> informes = informeService.generarInforme(request.getOrganizaciones(),
                request.getFechaInicial(), request.getFechaFinal());
        return ResponseEntity.ok(informes);
    }

    @GetMapping("/organizaciones")
    public ResponseEntity<List<String>> getOrganizaciones() {
        List<String> organizaciones = informeService.obtenerOrganizaciones();
        return ResponseEntity.ok(organizaciones);
    }
}