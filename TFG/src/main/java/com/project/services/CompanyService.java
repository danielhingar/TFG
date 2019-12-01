package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Company;
import com.project.repositories.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private CompanyRepository companyRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	@Transactional(readOnly = true)
	public List<Company> findAll() {
		return (List<Company>) companyRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Company findById(Long id) {
		return companyRepository.findById(id).orElse(null);

	}

}
