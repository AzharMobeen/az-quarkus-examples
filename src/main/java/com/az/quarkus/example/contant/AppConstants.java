package com.az.quarkus.example.contant;

public final class AppConstants {

    private AppConstants(){}

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String CLAIMS = "claims";
    public static final String ROLES = "roles";
    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String[] ROLES_ARRAY = {ADMIN, USER};
}
