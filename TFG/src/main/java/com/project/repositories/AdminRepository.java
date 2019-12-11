package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Admin;
import com.project.domain.Client;
import com.project.domain.Reporter;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	@Query("select a from Admin a where a.username=?1")
	Admin findByUsername(String username);
	
	@Query("select c from Client c where c.enabled = false")
	List<Client> findBannedClients();
	
	@Query("select c from Company c where c.enabled = false")
	List<Client> findBannedCompany();
	
	@Query("select r from Reporter r where r.enabled = false")
	List<Reporter> findBannedReporter();

	
	//QUERIES DASHBOARD//
	
	@Query("select f.company from Facture f where f.status = 'ACCEPT' group by f.company.id order by count(f) desc")
	List<String> findTopCompaniesByProducts();
	

	
}
