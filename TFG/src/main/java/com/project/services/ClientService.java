package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.domain.Claim;
import com.project.domain.Client;
import com.project.domain.Facture;
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
	private FactureService factureService;
	
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private BasketService basketService;
	
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

	// ----------------------------------------Delete---------------------------
	@Transactional
	public void delete(int id) {
		List<Facture> factures=this.factureService.findFactureByClient(id);
		for(int i=0;i<factures.size();i++) {
			List<Claim> claims=this.claimService.findClaimByFacture(factures.get(i).getId());
			for(int j=0;j<claims.size();j++) {
				this.claimService.delete(claims.get(j).getId());
			}
			
			this.factureService.delete(factures.get(i).getId());
		}
		
		Basket b=this.findById(id).getBasket();
		this.basketService.delete(b.getId());
		this.clientRepository.deleteById(id);
	}

}
