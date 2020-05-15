package com.project.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Client extends Usuario {
	
	private String address;
	private String number;
	private String codePostal;
	private String locality;
	private String province;


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getLocality() {
		return locality;
	}


	public void setLocality(String locality) {
		this.locality = locality;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}

	// Relationships ---------------------------------------------------------
	private Basket basket;	
	private List<Product> wishProducts;
	
	public Client() {
		basket=new Basket();
		wishProducts= new ArrayList<Product>();
	}


	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="client_id")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "hadler" })
	public List<Product> getWishProducts() {
		return wishProducts;
	}

	public void setWishProducts(List<Product> wishProducts) {
		this.wishProducts = wishProducts;
	}
	
	
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "basket_id")
	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
