package com.project.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Range;

@Entity
public class Shipping implements Serializable {

	// Attributes---------------------------------------------------------------------
	private int id;
	private String title;
	private double price;
	private int dateMin; 
	private int dateMax;
	private String observation;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Min(0)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Range(min = 1,max = 30)
	public int getDateMin() {
		return dateMin;
	}

	public void setDateMin(int dateMin) {
		this.dateMin = dateMin;
	}
	@Range(min = 1,max= 30)
	public int getDateMax() {
		return dateMax;
	}

	public void setDateMax(int dateMax) {
		this.dateMax = dateMax;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	// Relationships--------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
