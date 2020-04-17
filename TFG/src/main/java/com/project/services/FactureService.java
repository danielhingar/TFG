package com.project.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.domain.Client;
import com.project.domain.Company;
import com.project.domain.Facture;
import com.project.domain.ItemBasket;
import com.project.domain.Product;
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

	@Autowired
	private ItemBasketService itemBasketService;
	
	@Autowired
	private ProductService productService;

	// --------------------------------------------Methods----------------------------------------------------------

	// ----------------------list factures by client-----------------------
	@Transactional(readOnly = true)
	public Page<Facture> findFactureByClient(String username, Pageable pageable) {
		Page<Facture> factures = factureRepository.findFacturesByClient(username, pageable);
		return factures;
	}

	// ----------------------list factures by client-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFacturesPendingByClient(String username) {
		return factureRepository.findFacturesPendingByClient(username);
	}

	// ----------------------list factures by client-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFacturesClient(String username) {
		return factureRepository.findFacturesClient(username);
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public Page<Facture> findFactureByCompany(String username, Pageable pageable) {
		return factureRepository.findFactureByCompany(username, pageable);
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFactureByCompany(String username) {
		return factureRepository.findFactureByCompany(username);
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public Page<Facture> findFacturesAllClients(Pageable pageable) {
		return factureRepository.findFacturesAllClient(pageable);
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public Page<Facture> findFactureAllCompany(Pageable pageable) {
		return factureRepository.findFacturesAllPagada(pageable);
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
		Client c = this.clientService.findByUsername(username);
		Basket b = c.getBasket();
		Set<Company> companies = this.createsFactures(username);
		List<Company> companies1 = new ArrayList<Company>(companies);
		for (int x = 0; x < companies1.size(); x++) {
			Facture facture1 = new Facture();
			List<ItemBasket> items = new ArrayList<ItemBasket>();
			facture1.setCompany(companies1.get(x));
			for (int i = 0; i < b.getItemBaskets().size(); i++) {
				companies.add(b.getItemBaskets().get(i).getProduct().getCompany());
				if (b.getItemBaskets().get(i).getProduct().getCompany().getId() == (companies1.get(x).getId())) {
					ItemBasket itemNew = new ItemBasket();

					itemNew.setCapacity(b.getItemBaskets().get(i).getCapacity());
					itemNew.setProduct(b.getItemBaskets().get(i).getProduct());
					itemNew.setQuantity(b.getItemBaskets().get(i).getQuantity());
					itemNew.setSize(b.getItemBaskets().get(i).getSize());
					itemNew.setStatus("PAGADO");
					this.itemBasketService.save(itemNew);
					items.add(itemNew);
				}
			}

			facture1.setItemBaskets(items);
			facture1.setCreateDate(new Date());
			facture1.setStatus("PENDIENTE DE PAGO");
			facture1.setClient(null);
			this.factureRepository.save(facture1);
			factures.add(facture1);

		}

		return factures;

	}

	@Transactional
	public Facture saveFactureUnify(Facture facture, String username) {
		Client c = this.clientService.findByUsername(username);
		List<ItemBasket> items = c.getBasket().getItemBaskets();
		for(ItemBasket i: items) {
			Product a = i.getProduct();
			a.setStock(a.getStock()-i.getQuantity());
			this.productService.saveProduct(a);
			if(a.getStock()== 0) {
				a.setStatus("SINSTOCK");
				this.productService.saveProduct(a);
			}
		}
		List<ItemBasket> newItems = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {

			this.itemBasketService.save(items.get(i));
			newItems.add(items.get(i));
		}
		facture.setAddress(c.getAddress());
		facture.setLocality(c.getLocality());
		facture.setNumber(c.getNumber());
		facture.setPostalCode(c.getCodePostal());
		facture.setProvince(c.getProvince());
		facture.setName(c.getName());
		facture.setSurnames(c.getSurnames());
		facture.setPhone(c.getPhone());
		facture.setItemBaskets(newItems);
		facture.setCreateDate(new Date());
		facture.setStatus("PENDING");
		facture.setClient(this.clientService.findByUsername(username));
		facture.setCompany(null);

		this.factureRepository.save(facture);

		return facture;

	}

	// --------------------Save update-------------------
	@Transactional
	public Facture save(Facture facture) {
		return this.factureRepository.save(facture);
	}

	@Transactional
	public Facture saveUpdateClient(Facture facture, String username) {
		Client c = this.clientService.findByUsername(username);
		c.getBasket().setItemBaskets(new ArrayList<>());
		this.basketService.save(c.getBasket());
		facture.setStatus("PAGADO");
		List<Facture> factures = this.findFacturesPendingByClient(username);
		for (Facture f : factures) {
			factureRepository.delete(f);
		}
		return this.factureRepository.save(facture);
	}

	@Transactional
	public Facture payCompany(Facture facture) {
		facture.setStatus("PAGADA");
		return this.factureRepository.save(facture);
	}

	// ----------------------------------------Delete----------------------------------------------------
	@Transactional
	public void delete(int id) {
		this.factureRepository.deleteById(id);
	}
}
