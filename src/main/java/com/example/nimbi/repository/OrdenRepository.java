package com.example.nimbi.repository;

import com.example.nimbi.model.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends MongoRepository<Orden, String> {
}
