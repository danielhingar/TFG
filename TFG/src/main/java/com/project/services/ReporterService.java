package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.domain.Reporter;
import com.project.domain.Role;
import com.project.repositories.ReporterRepository;


@Service
@Transactional
public class ReporterService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ReporterRepository reporterRepository;

	// Services----------------------------------------------------------------------------------------------------
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// CRUD--------------------------------------------------------------------------------------------------------
	
	//--------------------------------------Create-------------------------------------------------------
	@Transactional
	public Reporter save(Reporter reporter) {
		reporter.setPassword(bCryptPasswordEncoder.encode(reporter.getPassword()));
		List<Role> r = new ArrayList<>();
		Role role = roleService.findById(3);
		r.add(role);
		reporter.setRoles(r);

		return this.reporterRepository.save(reporter);
	}

	//---------------------------------------List---------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Reporter> findAll() {
		return (List<Reporter>) reporterRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Reporter findById(int id) {
		return reporterRepository.findById(id).orElse(null);

	}

}
