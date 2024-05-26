package com.az.quarkus.example.repository;

import com.az.quarkus.example.entity.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
