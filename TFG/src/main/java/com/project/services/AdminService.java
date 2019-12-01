package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Admin;
import com.project.repositories.AdminRepository;

@Service
@Transactional
public class AdminService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private AdminRepository adminRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------LIST------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Admin> findAll() {
		return (List<Admin>) adminRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Admin findById(Long id) {
		return adminRepository.findById(id).orElse(null);

	}

}
