package com.az.quarkus.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import com.az.quarkus.example.entity.Fruit;

import java.util.List;

@ApplicationScoped
@Slf4j
public class FruitRepository implements PanacheRepository<Fruit> {

    public List<Fruit> findByColor(String color) {
        return find("color", color).list();
    }
}