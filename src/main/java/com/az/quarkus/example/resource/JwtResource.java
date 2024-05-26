package com.az.quarkus.example.resource;

import com.az.quarkus.example.service.JwtService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Path("/api/token")
@RequiredArgsConstructor
public class JwtResource {

    private final JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateToken(UserRequest userRequest) {
        String token = jwtService.generateToken(userRequest.getUsername(), userRequest.getRoles());
        return Response.ok(new TokenResponse(token)).build();
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class UserRequest {
        private String username;
        private List<String> roles;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class TokenResponse {
        private String token;

        public TokenResponse(String token) {
            this.token = token;
        }

    }
}