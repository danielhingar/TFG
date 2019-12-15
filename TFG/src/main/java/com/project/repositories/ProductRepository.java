package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
}
