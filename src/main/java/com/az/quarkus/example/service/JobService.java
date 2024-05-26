package com.az.quarkus.example.service;

import java.util.Map;

public interface JobService {
    Map<String, Object> searchJobsWithFacets(int page, int size);
}
