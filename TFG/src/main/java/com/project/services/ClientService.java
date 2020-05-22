package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.domain.Client;
import com.project.domain.Product;
import com.project.domain.Role;
import com.project.repositories.ClientRepository;

@Service
@Transactional
public class ClientService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ClientRepository clientRepository;

	// Services----------------------------------------------------------------------------------------------------

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ProductService productService;


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
	
	@Transactional(readOnly=true)
	public Client findByUsername(String username) {
		return clientRepository.findClientByUsername(username);
	}

	// ----------------------------------------Create--------------------------------------------------------
	@Transactional
	public Client save(Client client) {
		client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
		List<Role> r = new ArrayList<>();
		Role role = roleService.findById(1);
		r.add(role);
		client.setRoles(r);
		client.setEnabled(true);
		return clientRepository.save(client);
	}
	
	// -------------------------------------Update---------------------------------------------------------
	@Transactional
	public Client update(Client client) {
		return clientRepository.save(client);
	}

	@Transactional
	public Client addProductToWish(Client client, int productId) {
		List<Product> wishBefore= client.getWishProducts();
		wishBefore.add(this.productService.findById(productId));
		return this.clientRepository.save(client);
	}
	
	@Transactional
	public Client removeProductToWish(Client client, int productId) {
		List<Product> wishBefore= client.getWishProducts();
		wishBefore.remove(productService.findById(productId));
		return this.clientRepository.save(client);
	}
	

}
