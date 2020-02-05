package com.project.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.domain.Company;
import com.project.domain.Facture;
import com.project.domain.ItemBasket;
import com.project.repositories.FactureRepository;

@Service
@Transactional
public class FactureService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private FactureRepository factureRepository;

	// Services----------------------------------------------------------------------------------------------------

	@Autowired
	private BasketService basketService;

	@Autowired
	private ClientService clientService;

	// --------------------------------------------Methods----------------------------------------------------------

	// ----------------------list factures by client-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFactureByClient(int clientId) {
		return factureRepository.findFacturesByClient(clientId);
	}

	// ----------------------list factures by client-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFacturesPendingByClient(String username) {
		return factureRepository.findFacturesPendingByClient(username);
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFactureByCompany(int companyId) {
		return factureRepository.findFactureByCompany(companyId);
	}

	// ----------------------Show a facture--------------------------------
	@Transactional(readOnly = true)
	public Facture findById(int id) {
		return factureRepository.findById(id).orElse(null);

	}

	// ----------------Create a facture--------------------------------
	public Set<Company> createsFactures(String username) {
		Basket b = this.basketService.findByClient(username);
		List<ItemBasket> items = b.getItemBaskets();
		Set<Company> companies = new HashSet<Company>();
		for (int i = 0; i < items.size(); i++) {
			companies.add(items.get(i).getProduct().getCompany());
		}
		return companies;
	}

	@Transactional
	public List<Facture> save(Facture facture, String username) {
		List<Facture> factures = new ArrayList<Facture>();
		Basket b = this.basketService.findByClient(username);
		Set<Company> companies = this.createsFactures(username);
		List<Company> companies1 = new ArrayList<Company>(companies);
		for (int x = 0; x < companies1.size(); x++) {
			Facture facture1 = new Facture();
			List<ItemBasket> items = new ArrayList<ItemBasket>();
			facture1.setCompany(companies1.get(x));
			for (int i = 0; i < b.getItemBaskets().size(); i++) {
				companies.add(b.getItemBaskets().get(i).getProduct().getCompany());
				if (b.getItemBaskets().get(i).getProduct().getCompany().getId() == (companies1.get(x).getId())) {
					items.add(b.getItemBaskets().get(i));
				}
			}
//			Basket b1 = new Basket();
//			b1.setItemBaskets(items);
//			this.basketService.save(b1);
			// this.factureRepository.flush();
			facture1.setItemBaskets(items);
			facture1.setCreateDate(new Date());
			facture1.setStatus("PENDING");
			facture1.setClient(this.clientService.findByUsername(username));
			this.factureRepository.save(facture1);
			factures.add(facture1);
			
		}
		b.setItemBaskets(new ArrayList<>());
		this.basketService.save(b);
		return factures;
		

	}

	// --------------------Save update-------------------
	@Transactional
	public Facture save(Facture facture) {
		return this.factureRepository.save(facture);
	}

	// ----------------------------------------Delete----------------------------------------------------
	@Transactional
	public void delete(int id) {
		this.factureRepository.deleteById(id);
	}
}
