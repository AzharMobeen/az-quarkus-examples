package com.az.quarkus.example.service;

import com.az.quarkus.example.model.Fruit;

import java.util.List;
import java.util.Optional;

public interface FruitService {
    Iterable<Fruit> findAll();

    void deleteById(long id);

    Fruit save(Fruit fruit);

    Optional<Fruit> findById(Long id);

    Fruit update(Long id, String color);

    List<Fruit> findByColor(String color);
}
