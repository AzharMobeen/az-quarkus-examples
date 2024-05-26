package com.az.quarkus.example.filter;

import com.az.quarkus.example.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Priority;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.az.quarkus.example.contant.AppConstants.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Slf4j
@Provider
@Priority(Priorities.AUTHENTICATION)
@RequiredArgsConstructor
public class JwtRequestFilter implements ContainerRequestFilter {

    private final JwtService jwtService;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        log.info("JwtRequest Filter start filtering");

        String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        if (requiresTokenValidation()) {

            if (isNull(authorizationHeader) || !authorizationHeader.startsWith(BEARER_PREFIX)) {
                log.error("Authorization header must be provided");
                throw new NotAuthorizedException("Authorization header must be provided");
            }

            String token = authorizationHeader.substring(BEARER_PREFIX.length()).trim();
            try {
                Claims claims = jwtService.validateToken(token);
                requestContext.setProperty(CLAIMS, claims);
                // Check if the token roles match the endpoint's required roles
                if (!hasMatchingRoles(claims)) {
                    log.error("User is not authorized to access this resource");
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
                }
            } catch (Exception e) {
                log.error("Invalid token ", e);
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }

    private boolean requiresTokenValidation() {
        return resourceInfo.getResourceMethod().isAnnotationPresent(RolesAllowed.class);
    }

    private RolesAllowed getRolesAllowedAnnotation() {
        // Check method level annotation
        Method resourceMethod = resourceInfo.getResourceMethod();
        if (resourceMethod.isAnnotationPresent(RolesAllowed.class)) {
            return resourceMethod.getAnnotation(RolesAllowed.class);
        }
        // Check class level annotation
        Class<?> resourceClass = resourceInfo.getResourceClass();
        if (resourceClass.isAnnotationPresent(RolesAllowed.class)) {
            return resourceClass.getAnnotation(RolesAllowed.class);
        }
        return null;
    }

    private boolean hasMatchingRoles(Claims claims) {
        RolesAllowed rolesAllowed = getRolesAllowedAnnotation();
        if (nonNull(rolesAllowed)) {
            String[] allowedRoles = rolesAllowed.value();
            if (allowedRoles.length == 0) {
                return true;
            }
            // Check if any of the allowed roles match the roles in the token
            List<String> userClaims = claims.get(ROLES, ArrayList.class);
            return Arrays.stream(allowedRoles).anyMatch(userClaims::contains);
        }
        return false;
    }
}