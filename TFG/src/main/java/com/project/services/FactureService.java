package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.domain.Facture;

import com.project.repositories.FactureRepository;


@Service
@Transactional
public class FactureService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private FactureRepository factureRepository;

	// Services----------------------------------------------------------------------------------------------------
	@Autowired
	private ClientService clientService;
	// --------------------------------------------Methods----------------------------------------------------------
	
	@Transactional(readOnly = true)
	public List<Facture> findFactureByClient(Long clientId){
		return factureRepository.findFacturesByClient(clientId);
	}
	
	@Transactional(readOnly = true)
	public List<Facture> findFactureByCompany(Long companyId){
		return factureRepository.findFactureByCompany(companyId);
	}

}
