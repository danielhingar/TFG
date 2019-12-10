package com.project.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Client extends Usuario {

	// Relationships ---------------------------------------------------------
	private Basket basket;

	@NotNull
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "hadler" })
	@OneToOne(fetch = FetchType.LAZY)
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
