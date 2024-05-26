package com.az.quarkus.example.resource;

import com.az.quarkus.example.dto.TokenRequest;
import com.az.quarkus.example.dto.TokenResponse;
import com.az.quarkus.example.service.JwtService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.az.quarkus.example.contant.AppConstants.ADMIN;
import static com.az.quarkus.example.contant.AppConstants.USER;

@Path("/api")
@RequiredArgsConstructor
public class JwtResource {

    private static final Logger log = LoggerFactory.getLogger(JwtResource.class);
    private final JwtService jwtService;

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateToken(TokenRequest tokenRequest) {
        String token = jwtService.generateToken(tokenRequest.getUsername(), tokenRequest.getRoles());
        return Response.ok(new TokenResponse(token)).build();
    }

    @GET
    @Path("/secure")
    @RolesAllowed({ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response secureEndpointForAdmin() {
        log.info("secureEndpointForAdmin endpoint /secure called!");
        return Response.ok(Map.of("message", "This is a secure endpoint for admin only")).build();
    }

    @GET
    @Path("/secure2")
    @RolesAllowed({ADMIN, USER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response secureEndpoint() {
        log.info("secureEndpoint endpoint /secure2 called!");
        return Response.ok(Map.of("message", "This is a secure endpoint for admin or user")).build();
    }
}