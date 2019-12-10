package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.domain.Usuario;
import com.project.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioService{

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private UsuarioRepository usuarioRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------LIST------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

//	@Override
//	@Transactional(readOnly = true)
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Usuario user= usuarioRepository.findByUsername(username);
//		
//	
//	}

}
