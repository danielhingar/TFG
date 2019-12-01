package com.project.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	private List<ItemBasket> itemBaskets;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="basket_id")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "hadler" })
	public List<ItemBasket> getItemBaskets() {
		return itemBaskets;
	}

	public void setItemBaskets(List<ItemBasket> itemBaskets) {
		this.itemBaskets = itemBaskets;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
