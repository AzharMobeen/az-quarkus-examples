package com.az.quarkus.example.resource.service.impl;

import com.az.quarkus.example.resource.client.DummyClient;
import com.az.quarkus.example.resource.service.GreeterService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@ApplicationScoped
public class GreeterServiceImpl implements GreeterService {

    @RestClient
    private DummyClient dummyClient;

    @Override
    public void greet() {
        log.info("greet method called!");
    }

    @Override
    public String greetFromClient() {
        log.info("Greet from Client");
        return dummyClient.greet();
    }
}
