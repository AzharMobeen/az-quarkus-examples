package com.az.quarkus.example.service.impl;

import com.az.quarkus.example.entity.Fruit;
import com.az.quarkus.example.repository.FruitRepository;
import com.az.quarkus.example.service.FruitService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    @Override
    public Iterable<Fruit> findAll() {
        log.info("Find all method called!");
        return fruitRepository.findAll().stream().toList();
    }

    @Override
    public void deleteById(long id) {
        log.info("deleteById method called!");
        fruitRepository.deleteById(id);
    }

    @Override
    public Fruit save(Fruit fruit) {
        log.info("save method called!");
         fruitRepository.persist(fruit);
         return fruit;
    }

    @Override
    public Optional<Fruit> findById(Long id) {
        log.info("findById method called!");
        return Optional.ofNullable(fruitRepository.findById(id));
    }

    @Override
    public Fruit update(Long id, String color) {
        log.info("update method called!");
        Optional<Fruit> optional = Optional.ofNullable(fruitRepository.findById(id));
        if (optional.isPresent()) {
            Fruit fruit = optional.get();
            fruit.setColor(color);
            fruitRepository.persist(fruit);
            return fruit;
        }
        throw new IllegalArgumentException("No Fruit with id " + id + " exists");
    }

    @Override
    public List<Fruit> findByColor(String color) {
        log.info("findByColor method called!");
        return fruitRepository.findByColor(color);
    }
}