package com.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Company extends Usuario{

	
	// Attributes---------------------------------------------------------------
	private String businessName;
	private String category;
	private String image;
	@Column(name = "create_date")
	private Date createDate;

	public Company() {
		this.about=new About();
		this.products=new ArrayList<Product>();
	}
	
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
	
	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// Relationships------------------------------------------------------------

	private About about;

	private List<Product> products;


	@JsonIgnoreProperties(value = { "company", "hibernateLazyInitializer", "hadler" },allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@JsonIgnoreProperties(value={"hibernateLazyInitializer", "hadler" },allowSetters = true)
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
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
