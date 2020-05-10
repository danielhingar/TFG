package com.project.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable {

	// Attributes------------------------------------------------------

	// Attributes------------------------------------------------------

		private int id;
		private String name;
		private String username;
		private String password;
		private String surnames;
		private String email;
		private Boolean enabled;
		private String phone;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@NotBlank(message = "nombre no puede estar vacío")
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Column(unique = true)
		@Size(min = 4, max = 20, message = "usuario tiene que tener una longitud entre 4 y 20 caracteres")
		@NotBlank(message = "usuario no puede estar vacío")
		public String getUsername() {
			return username;
		}
 
		public void setUsername(String username) {
			this.username = username;
		}

		@NotBlank(message = "contraseña no puede estar vacío")
		//@Pattern(regexp = "^ (? =. * [0-9] +. *) (? =. * [A-zA-Z] +. *) [0-9a-zA-Z] {6,} $", message = "La contraseña tiene que tener una longitud de 6 caracteres y contener al menos una letra")
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@NotBlank(message = "apellidos no puede estar vacío")
		public String getSurnames() {
			return surnames;
		}

		public void setSurnames(String surnames) {
			this.surnames = surnames;
		}

		@NotBlank(message = "email no puede estar vacío")
		@Email(message = "email no corresponde con un formato válido de email")
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		// Relationships ---------------------------------------------------------
		
		private List<Role> roles;
		
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinTable(name="usuario_roles",joinColumns = @JoinColumn(name="usuario_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}


		

		// ------------------------------------------------------------------------------
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;


}
