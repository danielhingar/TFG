package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.domain.Reporter;

@Repository
public interface ReporterRepository extends JpaRepository<Reporter, Integer> {

	@Query("select c from Reporter c where c.username = ?1")
	Reporter findReporterByUsername(String username);
}
