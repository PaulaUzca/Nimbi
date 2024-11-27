package com.example.nimbi.controller;

import com.example.nimbi.model.Counter;
import com.example.nimbi.model.Orden;
import com.example.nimbi.model.Price;
import com.example.nimbi.service.MongoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mongo")
public class MongoDataController {

    @Autowired
    private MongoDataService mongoDataService;

    // Endpoint para obtener todos los Counters
    @GetMapping("/counters")
    public ResponseEntity<List<Counter>> getAllCounters() {
        return ResponseEntity.ok(mongoDataService.getAllCounters());
    }

    // Endpoint para obtener todas las Ordenes
    @GetMapping("/ordenes")
    public ResponseEntity<List<Orden>> getAllOrdenes() {
        return ResponseEntity.ok(mongoDataService.getAllOrdenes());
    }

    // Endpoint para obtener todos los Prices
    @GetMapping("/prices")
    public ResponseEntity<List<Price>> getAllPrices() {
        return ResponseEntity.ok(mongoDataService.getAllPrices());
    }
}
