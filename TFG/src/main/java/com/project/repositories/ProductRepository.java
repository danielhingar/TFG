package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
