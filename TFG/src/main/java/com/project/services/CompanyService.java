package com.project.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.domain.Company;
import com.project.domain.Role;
import com.project.repositories.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private CompanyRepository companyRepository;

	// Services----------------------------------------------------------------------------------------------------

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleService roleService;

	// -------------------------------------List companies order
	// random------------------------------------------------------------------

	@Transactional(readOnly = true)
	public List<Company> findAllRandom() {
		List<Company> companies = companyRepository.findAll();
		Random rdm = new Random();
		Collections.shuffle(companies, rdm);
		return companies;
	}

	// ----------------------------------------Create--------------------------------------------------------
	@Transactional
	public Company save(Company company) {
		company.setPassword(bCryptPasswordEncoder.encode(company.getPassword()));
		List<Role> r = new ArrayList<>();
		Role role = roleService.findById(4);
		r.add(role);
		company.setRoles(r);
		company.setCreateDate(new Date());
		company.setEnabled(true);
		return companyRepository.save(company);
	}
	
	// -------------------------------------Update---------------------------------------------------------
	@Transactional
	public Company update(Company company) {
		return companyRepository.save(company);
	}

	// -------------------------------------List companies order
	// random------------------------------------------------------------------

	@Transactional(readOnly = true)
	public List<Company> findAll() {
		return (List<Company>) companyRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Company findById(int companyId) {
		return companyRepository.findById(companyId).orElse(null);

	}
	
	@Transactional(readOnly=true)
	public Company findByUsername(String username) {
		return companyRepository.findCompanyByUsername(username);
	}
	
	//--------update------------
	@Transactional
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}
	
	//------Paginable
	@Transactional(readOnly = true)
	public Page<Company> findAll(Pageable pageable){
		return companyRepository.findAll(pageable);
	}

	
}
