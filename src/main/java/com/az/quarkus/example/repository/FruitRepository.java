package com.az.quarkus.example.repository;

import com.az.quarkus.example.entity.Fruit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FruitRepository extends CrudRepository<Fruit, Long> {

    List<Fruit> findByColor(String color);
}