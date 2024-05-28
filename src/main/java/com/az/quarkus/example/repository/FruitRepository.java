package com.az.quarkus.example.repository;

import com.az.quarkus.example.model.Fruit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@ApplicationScoped
@Slf4j
public class FruitRepository implements PanacheRepository<Fruit> {

    public List<Fruit> findByColor(String color) {
        return find("color", color).list();
    }
}