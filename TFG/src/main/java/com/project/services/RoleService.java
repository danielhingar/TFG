package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Role;
import com.project.repositories.RoleRepository;

@Transactional
@Service
public class RoleService {
	
	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private RoleRepository roleRepository;
	
	
	//----------------------------------------Methods------------------------------------------------------------
	@Transactional
	public Role findById(int id) {
		return roleRepository.findById(id).orElse(null);
	}

}
