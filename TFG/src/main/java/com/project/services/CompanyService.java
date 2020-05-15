package com.project.services;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.domain.Company;
import com.project.domain.Facture;
import com.project.domain.ItemBasket;
import com.project.domain.Product;
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
	
	@Autowired
	private FactureService factureService;

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

	@Transactional(readOnly = true)
	public Company findByUsername(String username) {
		return companyRepository.findCompanyByUsername(username);
	}

	// --------update------------
	@Transactional
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}

	// ------Paginable
	@Transactional(readOnly = true)
	public Page<Company> findAll(Pageable pageable) {
		return companyRepository.findAll(pageable);
	}

	// ----------------------------------------Stadistic------------------------------------------------------
	@Transactional(readOnly = true)
	public Map<String, Integer> productByCategory(String username) {
		Map<String, Integer> res = new HashMap<>();
		Company company = this.companyRepository.findCompanyByUsername(username);
		List<String> categories = new ArrayList<String>();
		for (Product p : company.getProducts()) {
			categories.add(p.getCategory());
		}
		for (String c : categories) {
			Integer numProductByCategory = 0;
			for (Product p : company.getProducts()) {
				if (p.getCategory().equals(c)) {
					numProductByCategory = numProductByCategory + 1;
				}
			}
			res.put(c, numProductByCategory);
		}

		return res;
	}

	@Transactional(readOnly = true)
	public Map<String, Integer> productSoldByCompany(String username) {
		Map<String, Integer> res = new HashMap<>();
		List<Facture> factures = factureService.findFactureByCompany(username);
		Set<ItemBasket> items= new HashSet<>();
		List<ItemBasket> items2 = new ArrayList<ItemBasket>();
		for (Facture f : factures) {
			for(ItemBasket i: f.getItemBaskets()) {
				items.add(i);
				items2.add(i);
			}
			
		}
		for(ItemBasket i:items) {
			String nameProduct=i.getProduct().getName();
			Integer numProductSold = 0;
			for(ItemBasket i2:items2) {
				if(i2.getProduct().getName().equals(nameProduct)) {
					numProductSold = numProductSold +i2.getQuantity();
				}
			}
			res.put(i.getProduct().getName(), numProductSold);
		}
		

		return res;
	}
	
	@Transactional(readOnly = true)
	public Map<String, Integer> productInOffert(String username) {
		Map<String, Integer> res = new HashMap<>();
		Company company = this.companyRepository.findCompanyByUsername(username);
		Integer numProductOffert=0;
		for (Product p : company.getProducts()) {
			if(p.getOffert()!=null) {
	
				numProductOffert= numProductOffert+1;
			}
		}
		res.put("En oferta", numProductOffert);
		res.put("Sin oferta", company.getProducts().size()-numProductOffert);
		
		return res;
	}

}
