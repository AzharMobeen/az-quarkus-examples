package com.az.quarkus.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TokenRequest {
    private String username;
    private List<String> roles;
}