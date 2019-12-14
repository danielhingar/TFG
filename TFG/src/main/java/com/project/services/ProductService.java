package com.project.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.domain.Client;
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
	
	@Autowired
	private ClientService clientService;
	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------List------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Product findById(int id) {
		return this.productRepository.findById(id).orElse(null);

	}
	//-----------------------------------------Save----------------------------------------------------------
	@Transactional
	public Product saveProduct(Product product,int companyId) {
		product.setCompany(this.companyService.findById(companyId));
		product.setCreateDate(new Date());
		return productRepository.save(product);
	}
	
	//-----------------------------------------Save prueba------------------------------------------------------
	@Transactional
	public Product saveProductTest(Product product) {
		product.setCompany(this.companyService.findById(992));
		product.setCreateDate(new Date());
		return productRepository.save(product);
	}
	//----------------------------------------Save update-----------------------------------------------------
	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	//-----------------------------------------Delete----------------------------------------------------------
	@Transactional
	public void delete(int id) {
		productRepository.deleteById(id);
	}

	//-----------------------------------------Pagination----------------------------------------------------------
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable){
		return productRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAllByCompany(int companyId){
		Company c= companyService.findById(companyId);
		List<Product> products=c.getProducts();
		return products;
	}


}
