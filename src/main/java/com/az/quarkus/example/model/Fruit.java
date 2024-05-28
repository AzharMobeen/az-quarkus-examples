package com.az.quarkus.example.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public class Fruit extends PanacheEntity {

    private String name;
    private String color;

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }
}