package com.project.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("select p from Product p where p.company.username = ?1 and p.status != 'ELIMINADO'")
	Page<Product> findProductByCompany(String username, Pageable pageable);
	
	@Query("select p from Product p where p.company.username = ?1 and (p.status = 'DISPONIBLE' or p.status= 'SINSTOCK')")
	Page<Product> findProductByCompanyClient(String username, Pageable pageable);
	
	@Query("select p from Product p where p.category = ?1 and p.status != 'ELIMINADO'")
	List<Product> findProductByCategory(String category);
}
