package com.project.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.URL;

@Entity
public class About implements Serializable {

	// Attributes---------------------------------------------------------------
	private int id;
	private String address;
	private String facebook;
	private String instagram;
	private String twitter;
	private String youtube;
	private String polity;
	private String compromise;
	private String description;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
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


	
	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getPolity() {
		return polity;
	}

	public void setPolity(String polity) {
		this.polity = polity;
	}

	public String getCompromise() {
		return compromise;
	}

	public void setCompromise(String compromise) {
		this.compromise = compromise;
	}




	// Relationships------------------------------------------------------------



	// -------------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
