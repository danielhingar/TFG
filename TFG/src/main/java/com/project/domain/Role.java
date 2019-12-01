package com.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Role implements Serializable {

	// Attributes------------------------------------------------------------------------

	private Long id;

	private String nombre;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, length = 20)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Relationships-----------------------------------------------------------------


	// ------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
