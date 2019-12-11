package com.project.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

@Entity
public class About implements Serializable {

	// Attributes---------------------------------------------------------------
	private int id;
	private String address;
	private String facebook;
	private String instagram;
	private String description;
	private String images;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@URL
	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	@URL
	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	// Relationships------------------------------------------------------------
	
	// -------------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
