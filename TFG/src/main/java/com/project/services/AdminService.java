package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Admin;

import com.project.domain.Role;

import com.project.repositories.AdminRepository;

@Service
@Transactional
public class AdminService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Services----------------------------------------------------------------------------------------------------

	@Autowired
	private RoleService roleService;

	

	// CRUD--------------------------------------------------------------------------------------------------------

	// -----------------------------------------Save----------------------------------------------------------------
	@Transactional
	public Admin save(Admin admin) {
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		List<Role> r = new ArrayList<>();
		Role role = roleService.findById(2);
		r.add(role);
		admin.setRoles(r);

		return adminRepository.save(admin);
	}

	// ----------------------------------------List--------------------------------------------------
	@Transactional(readOnly = true)
	public List<Admin> findAll() {
		return (List<Admin>) adminRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Admin findById(int id) {
		return adminRepository.findById(id).orElse(null);

	}

}
