package com.project.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class Company extends User {

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

	@OneToOne(optional = true)
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
