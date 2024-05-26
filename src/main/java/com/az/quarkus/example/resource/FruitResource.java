package com.az.quarkus.example.resource;

import com.az.quarkus.example.entity.Fruit;
import com.az.quarkus.example.service.FruitService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/fruits")
@RequiredArgsConstructor
public class FruitResource {

    private final FruitService fruitService;

    @GET
    public Iterable<Fruit> findAll() {
        return fruitService.findAll();
    }

    @DELETE
    @Path("{id}")
    public void delete(long id) {
        fruitService.deleteById(id);
    }

    @POST
    @Path("/name/{name}/color/{color}")
    public Fruit create(String name, String color) {
        return fruitService.save(new Fruit(name, color));
    }

    @PUT
    @Path("/id/{id}/color/{color}")
    public Fruit changeColor(Long id, String color) {
        return fruitService.update(id, color);
    }

    @GET
    @Path("/color/{color}")
    public List<Fruit> findByColor(String color) {
        return fruitService.findByColor(color);
    }
}
