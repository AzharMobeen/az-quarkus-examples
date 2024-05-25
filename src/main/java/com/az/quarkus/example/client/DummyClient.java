package com.az.quarkus.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(baseUri = "https://mpdf1ccb873d77b9c539.free.beeceptor.com")
public interface DummyClient {

    @GET
    String greet();
}
