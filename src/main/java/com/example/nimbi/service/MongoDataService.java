package com.example.nimbi.service;

import com.example.nimbi.model.Counter;
import com.example.nimbi.model.Orden;
import com.example.nimbi.model.Price;
import com.example.nimbi.repository.CounterRepository;
import com.example.nimbi.repository.OrdenRepository;
import com.example.nimbi.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDataService {

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private PriceRepository priceRepository;

    // Métodos para manejar Counters
    public List<Counter> getAllCounters() {
        return counterRepository.findAll();
    }

    // Métodos para manejar Ordenes
    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    // Métodos para manejar Prices
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }
}
