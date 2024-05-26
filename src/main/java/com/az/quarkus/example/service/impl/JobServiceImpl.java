package com.az.quarkus.example.service.impl;

import com.az.quarkus.example.entity.Job;
import com.az.quarkus.example.service.JobService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final EntityManager entityManager;

    @Override
    public Map<String, Object> searchJobsWithFacets(int page, int size) {
        log.info("searchJobsWithFacets method called");
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> job = cq.from(Job.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.greaterThanOrEqualTo(job.get("postedAt"), sevenDaysAgo));

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Job> query = entityManager.createQuery(cq);
        if(page > 0 && size > 0) {
            query.setFirstResult(page * size);
            query.setMaxResults(size);
        }

        List<Job> results = query.getResultList();

        Map<String, Object> response = new HashMap<>();
        response.put("jobs", results);
        response.put("total", getTotalCount(sevenDaysAgo));
        response.put("facets", getFacets(sevenDaysAgo));
        return response;
    }

    private Long getTotalCount(LocalDateTime sevenDaysAgo) {
        log.info("getTotalCount method called");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Job> job = cq.from(Job.class);
        cq.select(cb.count(job));
        cq.where(cb.greaterThanOrEqualTo(job.get("postedAt"), sevenDaysAgo));
        return entityManager.createQuery(cq).getSingleResult();
    }

    private Map<String, Map<String, Long>> getFacets(LocalDateTime sevenDaysAgo) {
        log.info("getFacets method called");
        Map<String, Map<String, Long>> facets = new HashMap<>();
        facets.put("title", getFacetCounts("title", sevenDaysAgo));
        facets.put("location", getFacetCounts("location", sevenDaysAgo));
        facets.put("salary", getFacetCounts("salary", sevenDaysAgo));
        facets.put("jobType", getFacetCounts("jobType", sevenDaysAgo));
        return facets;
    }

    private Map<String, Long> getFacetCounts(String field, LocalDateTime sevenDaysAgo) {
        log.info("getFacetCounts method called");
        String jpql = String.format("SELECT j.%s, COUNT(j) FROM Job j WHERE j.postedAt >= :date GROUP BY j.%s", field, field);
        List<Object[]> results = entityManager.createQuery(jpql)
                .setParameter("date", sevenDaysAgo)
                .getResultList();

        Map<String, Long> facetCounts = new HashMap<>();
        for (Object[] result : results) {
            facetCounts.put(result[0].toString(), (Long) result[1]);
        }
        return facetCounts;
    }
}