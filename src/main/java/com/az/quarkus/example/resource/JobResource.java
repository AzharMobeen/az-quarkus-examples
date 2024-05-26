package com.az.quarkus.example.resource;

import com.az.quarkus.example.service.JobService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
@Path("/api/job")
public class JobResource {

    private final JobService jobService;

    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> searchJobsWithFacets(@QueryParam("page") int page, @QueryParam("size") int size) {
        return jobService.searchJobsWithFacets(page, size);
    }
}
