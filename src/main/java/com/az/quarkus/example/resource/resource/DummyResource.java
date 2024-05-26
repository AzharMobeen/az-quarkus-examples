package com.az.quarkus.example.resource.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greet")
public class DummyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String greet() {
        return "This is Dummy client response";
    }
}
