package com.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.domain.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	

}
