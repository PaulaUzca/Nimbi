package com.example.nimbi.service;

import com.example.nimbi.constantes.Constantes;
import com.example.nimbi.model.Cliente;
import com.example.nimbi.model.DetalleProducto;
import com.example.nimbi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public List<Cliente> buscarClientePorNombre(String nombre){
        Pageable pageable = PageRequest.of(0, 20); // Página 0, tamaño 20
        Page<Cliente> page = clienteRepository.findByNombre(nombre, pageable);

        return new ArrayList<>(page.getContent());
    }
}
