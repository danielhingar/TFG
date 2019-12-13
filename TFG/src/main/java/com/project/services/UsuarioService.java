package com.project.services;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.project.domain.Usuario;
import com.project.repositories.UsuarioRepository;


@Service
@Transactional
public class UsuarioService implements UserDetailsService{
	
	private Logger logger= LoggerFactory.getLogger(UsuarioService.class);
	

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

	//----------------------------------------Load user-----------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario= usuarioRepository.findByUsername(username);
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).peek(authority-> logger.info("Role :"+authority.getAuthority())).collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
	
	//-----------------------------------Find user by username-------------------------------------------
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		
		return usuarioRepository.findByUsername(username);
	}
	
	//----------------------------------- Get principal-------------------------------------------------
	public static String getPrincipal() {
		Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails=null;
		if(principal instanceof UserDetails) {
			userDetails=(UserDetails) principal;
		}
		return userDetails.getUsername();
	}

}
