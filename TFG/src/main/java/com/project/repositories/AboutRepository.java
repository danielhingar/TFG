package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.domain.About;

@Repository
public interface AboutRepository extends JpaRepository<About, Integer> {
	

}
