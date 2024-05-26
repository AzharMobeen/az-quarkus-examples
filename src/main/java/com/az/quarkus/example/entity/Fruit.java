package com.az.quarkus.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Fruit {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String color;

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
