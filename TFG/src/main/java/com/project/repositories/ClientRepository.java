package com.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Query("select c from Client c where c.username = ?1")
	Client findClientByUsername(String username);
}
