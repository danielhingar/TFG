package com.project.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;



@Entity
public class Basket implements Serializable {

	// Attributes------------------------------------------------------------------

	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Relationships------------------------------------------------------------------

	private List<Product> products;
	
	

	@ManyToMany(fetch = FetchType.LAZY)
	@Valid
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
