package com.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable {

	// Attributes------------------------------------------------------

	// Attributes------------------------------------------------------

		private Long id;
		private String name;
		private String username;
		private String password;
		private String surnames;
		private String email;
		private boolean enabled;
		private String phone;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@NotBlank
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Column(unique = true)
		@Size(min = 4, max = 20)
		@NotBlank
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@NotBlank
		@Column(length = 20)
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@NotBlank
		public String getSurnames() {
			return surnames;
		}

		public void setSurnames(String surnames) {
			this.surnames = surnames;
		}

		@NotBlank
		@Email
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		// Relationships ---------------------------------------------------------
		private Role role;

		@NotNull
		@JsonIgnoreProperties({"hibernateLazyInitializer", "hadler" })
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="role_id")
		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		// ------------------------------------------------------------------------------
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;


}
