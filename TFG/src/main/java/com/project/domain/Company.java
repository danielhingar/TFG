package com.project.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Company extends Usuario{

	
	// Attributes---------------------------------------------------------------
	private String businessName;
	private String category;
	private String image;

	
	@NotBlank
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@NotBlank
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// Relationships------------------------------------------------------------

	private About about;

	private List<Product> products;


	@JsonIgnoreProperties(value = { "company", "hibernateLazyInitializer", "hadler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@JsonIgnoreProperties({"hibernateLazyInitializer", "hadler" })
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="about_id")
	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	// -------------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
