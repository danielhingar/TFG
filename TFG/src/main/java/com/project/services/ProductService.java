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
	public Product saveProduct(Product product,String username) {
		product.setCompany(this.companyService.findByUsername(username));
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
		Product p=this.findById(id);
		List<Client> clients=this.clientService.findAll();
		for(Client t:clients) {
			Basket b=t.getBasket();
			for(int i=0;i<b.getItemBaskets().size();i++) {
				if(p.getName().equals(b.getItemBaskets().get(i).getProduct().getName())) {
					b.getItemBaskets().get(i).setProduct(null);
					b.getItemBaskets().remove(b.getItemBaskets().get(i));
				}
			}
		}
		productRepository.deleteById(id);
	}

	//-----------------------------------------Pagination----------------------------------------------------------
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable){
		return productRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAllByCompany(String username){
		Company c= companyService.findByUsername(username);
		List<Product> products=c.getProducts();
		return products;
	}


}
