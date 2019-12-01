package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Company;
import com.project.domain.Facture;
import com.project.domain.Product;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

//	@Query("select p From Product p where p.company.id = ?1")
//	List<Product> findProductByCompany(int companyId);
//	
//	@Query("select f From Facture f where f.company.id =?1")
//	List<Facture> findFactureByCompany(int companyId);
}
