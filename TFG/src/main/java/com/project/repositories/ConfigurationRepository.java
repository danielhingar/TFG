package com.project.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.project.domain.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
	

}
