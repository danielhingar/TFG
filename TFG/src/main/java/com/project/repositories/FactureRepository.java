package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer> {
	
	@Query("select f from Facture f where f.client.username = ?1")
	List<Facture> findFacturesByClient(String username);
	
	@Query("select f from Facture f where f.status='ACCEPTED' and f.company.username =?1")
	List<Facture> findFactureByCompany(String username);
	
	@Query("select f from Facture f where f.status='PENDING' and f.client.username = ?1")
	Facture findFacturesPendingByClient(String username);
}
