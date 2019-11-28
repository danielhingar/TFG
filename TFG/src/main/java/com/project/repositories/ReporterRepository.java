package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Claim;
import com.project.domain.Reporter;

@Repository
public interface ReporterRepository extends JpaRepository<Reporter, Long> {

	@Query("select c from Claim c where c.reporter.id = ?1")
	List<Claim> findClaimByReporter(int reporterId);
}
