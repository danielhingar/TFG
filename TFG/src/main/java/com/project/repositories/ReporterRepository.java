package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.project.domain.Reporter;

@Repository
public interface ReporterRepository extends JpaRepository<Reporter, Long> {

}
