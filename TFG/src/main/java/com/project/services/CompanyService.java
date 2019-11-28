package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Product;
import com.project.repositories.CompanyRepository;

@Service
@Transactional
public class CompanyService {
	
	//Repository
	@Autowired
	private CompanyRepository companyRepository;
	
	
	public List<Product> findProductByCompany(int companyId){
		return this.companyRepository.findProductByCompany(companyId);
	}
	
	public void a(){
		System.out.println(findProductByCompany(1));
	}
	
	

}
