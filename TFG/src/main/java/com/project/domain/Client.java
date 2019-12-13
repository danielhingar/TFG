package com.project.domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Client extends Usuario {

	// Relationships ---------------------------------------------------------
	private Basket basket;

	public Client() {
		basket=new Basket();
	}
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "hadler" })
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
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
