package com.example.nimbi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proveedor")
@Data // Genera getters, setters, toString, equals, hashCode, etc.
@NoArgsConstructor // Genera un constructor vacío
@AllArgsConstructor // Genera un constructor con todos los campos

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproveedor")
    private Long id;

    private String nombre;



    // Relaciones

    @OneToMany(mappedBy = "proveedor") // Relación con Lote
    private List<Lote> lotes; // Lista de lotes asociados al proveedor
}
