package com.project.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Product implements Serializable {

	// Atributes-------------------------------------------------------------------
	private int id;
	private String name;
	private String description;
	private String photo;
	private double price;
	@Column(name = "create_date")
	private Date createDate;
	private String category;
	private String size;
	private String height;
	private String width;
	private String weight;
	private String inch;
	private String capacity;
	private String brand;
	private Integer offert;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Min(0)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	@Temporal(TemporalType.DATE)
	@Past
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@NotBlank
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getInch() {
		return inch;
	}

	public void setInch(String inch) {
		this.inch = inch;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@NotBlank
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Range(min = 5, max = 90)
	public Integer getOffert() {
		return offert;
	}

	public void setOffert(Integer offert) {
		this.offert = offert;
	}

	// Relationships-----------------------------------------------------------------

	
	private Company company;
	
	@JsonIgnoreProperties({ "products", "hibernateLazyInitializer", "hadler" })
	@ManyToOne(fetch = FetchType.LAZY)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	// ------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
