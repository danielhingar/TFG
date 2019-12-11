package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Usuario;


@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername(String username);

}
