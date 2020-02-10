package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {
	@Query("select c from Claim c where c.facture.id = ?1")
	List<Claim> findClaimByFacture(int factureId);
	
	@Query("select c from Claim c where c.reporter.username= ?1")
	List<Claim> findClaimByReporter(String reporterId);
	
	@Query("select c from Claim c where c.facture.client.username = ?1")
	List<Claim> findClaimByClient(String username);
}
