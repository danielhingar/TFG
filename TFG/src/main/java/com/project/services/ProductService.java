package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Company;
import com.project.domain.Product;
import com.project.repositories.ProductRepository;


@Service
@Transactional
public class ProductService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ProductRepository productRepository;

	// Services----------------------------------------------------------------------------------------------------
	@Autowired
	private CompanyService companyService;
	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------List------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);

	}
	//-----------------------------------------Save----------------------------------------------------------
	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	//-----------------------------------------Delete----------------------------------------------------------
	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	//-----------------------------------------Pagination----------------------------------------------------------
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable){
		return productRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAllByCompany(Long companyId){
		Company c= companyService.findById(companyId);
		List<Product> products=c.getProducts();
		return products;
	}

}
