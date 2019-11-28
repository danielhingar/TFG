package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Claim;
import com.project.domain.Client;
import com.project.domain.Facture;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("select f from Facture f where f.client.id = ?1")
	List<Facture> findFacturesByClient(int clientId);
	
	@Query("select c from Claim c where c.facture.id = ?1")
	List<Claim> findClaimByFacture(int clientId);
}
