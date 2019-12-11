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
//	@Autowired
//	private ClientService clientService;
	// --------------------------------------------Methods----------------------------------------------------------
	
	//----------------------------------------list factures by client------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Facture> findFactureByClient(int clientId){
		return factureRepository.findFacturesByClient(clientId);
	}
	
	//---------------------------------------list factures by company---------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Facture> findFactureByCompany(int companyId){
		return factureRepository.findFactureByCompany(companyId);
	}
	
	//---------------------------------------Show a facture--------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Facture findById(int id) {
		return factureRepository.findById(id).orElse(null);

	}
	

}
