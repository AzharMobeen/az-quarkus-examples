package com.az.quarkus.example.resource;

import com.az.quarkus.example.service.GreeterService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Path("/hello")
@Slf4j
@RequiredArgsConstructor
public class ExampleResource {

    private final GreeterService greeterService;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.info("Resource hello method called!");
        greeterService.greet();
        return "Hello from Quarkus REST";
    }

    @Path("/testClient")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testGreetClient() {
        log.info("Resource greet method called!");
        return greeterService.greetFromClient();
    }
}
