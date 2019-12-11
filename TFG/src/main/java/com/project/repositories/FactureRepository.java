package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer> {
	
	@Query("select f from Facture f where f.client.id = ?1")
	List<Facture> findFacturesByClient(int clientId);
	
	@Query("select f from Facture f where f.company.id =?1")
	List<Facture> findFactureByCompany(int companyId);
	

}
