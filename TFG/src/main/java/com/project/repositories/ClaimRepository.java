package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
	@Query("select c from Claim c where c.facture.id = ?1")
	List<Claim> findClaimByFacture(Long clientId);
}
