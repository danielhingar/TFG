package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Client;

import com.project.repositories.ClientRepository;

@Service
@Transactional
public class ClientService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ClientRepository clientRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------LIST------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientRepository.findAll();
	}

	// ----------------------------------------SHOW------------------------------------------------------
	@Transactional(readOnly = true)
	public Client findById(int id) {
		return clientRepository.findById(id).orElse(null);

	}

}
