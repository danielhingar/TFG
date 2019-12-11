package com.project.services;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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

	//-------------------------------------List companies order random------------------------------------------------------------------
	
	@Transactional(readOnly = true)
	public List<Company> findAllRandom() {
		List<Company> companies= companyRepository.findAll();
		Random rdm=new Random();
		Collections.shuffle(companies,rdm);
		return companies;
	}
	
	//-------------------------------------List companies order random------------------------------------------------------------------
	
	@Transactional(readOnly = true)
	public List<Company> findAll() {
		return (List<Company>) companyRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Company findById(int companyId) {
		return companyRepository.findById(companyId).orElse(null);

	}

}
