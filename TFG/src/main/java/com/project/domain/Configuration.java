package com.project.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
public class Configuration implements Serializable {

	// Attributes----------------------------------------------------------------------
	private int id;
	private String phone;
	private String email;
	private String facebook;
	private String instagram;
	private String twitter;
	private String address;
	private Boolean failSystem;
	private String nameSystem;
	private String banner;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank
	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	@NotBlank
	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	
	@NotBlank
	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public Boolean getFailSystem() {
		return failSystem;
	} 

	public void setFailSystem(Boolean failSystem) {
		this.failSystem = failSystem;
	}
	
	@NotBlank
	public String getNameSystem() {
		return nameSystem;
	}

	public void setNameSystem(String nameSystem) {
		this.nameSystem = nameSystem;
	}

	
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}




	// Relationships------------------------------------------------------------------






	// -------------------------------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
