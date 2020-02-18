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
	public List<Facture> findFactureByClient(String username) {
		List<Facture> factures = factureRepository.findFacturesByClient(username);
		Facture facturesPending = factureRepository.findFacturesPendingByClient(username);
		factures.remove(facturesPending);
		return factures;
	}

	// ----------------------list factures by client-----------------------
	@Transactional(readOnly = true)
	public Facture findFacturesPendingByClient(String username) {
		return factureRepository.findFacturesPendingByClient(username);
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFactureByCompany(String username) { 
		return factureRepository.findFactureByCompany(username);
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFacturesAllClients() {
		List<Facture> factures = factureRepository.findAll();
		List<Facture> facturesPending = factureRepository.findFacturesAllPending();
		List<Facture> facturesPagadas = factureRepository.findFacturesAllPagada();
		factures.removeAll(facturesPending);
		factures.removeAll(facturesPagadas);
		return factures;
	}

	// ------------------list factures by company-----------------------
	@Transactional(readOnly = true)
	public List<Facture> findFactureAllCompany() {
		return factureRepository.findFacturesAllPagada();
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
//					Product productNew = new Product();
//					productNew.setBrand(b.getItemBaskets().get(i).getProduct().getBrand());
//					productNew.setCapacity(b.getItemBaskets().get(i).getProduct().getCapacity());
//					productNew.setCategory(b.getItemBaskets().get(i).getProduct().getCategory());
//					productNew.setCreateDate(b.getItemBaskets().get(i).getProduct().getCreateDate());
//					productNew.setDescription(b.getItemBaskets().get(i).getProduct().getDescription());
//					productNew.setHeight(b.getItemBaskets().get(i).getProduct().getHeight());
//					productNew.setInch(b.getItemBaskets().get(i).getProduct().getInch());
//					productNew.setName(b.getItemBaskets().get(i).getProduct().getName());
//					productNew.setOffert(b.getItemBaskets().get(i).getProduct().getOffert());
//					productNew.setPhoto(b.getItemBaskets().get(i).getProduct().getPhoto());
//					productNew.setPrice(b.getItemBaskets().get(i).getProduct().getPrice());
//					productNew.setSize(b.getItemBaskets().get(i).getProduct().getSize());
//					productNew.setWeight(b.getItemBaskets().get(i).getProduct().getWeight());
//					productNew.setWidth(b.getItemBaskets().get(i).getProduct().getWidth());
//					productNew.setMemory(b.getItemBaskets().get(i).getProduct().getMemory());
//					productNew.setCompany(null);
//					this.productService.saveProduct(productNew);
					itemNew.setCapacity(b.getItemBaskets().get(i).getCapacity());
					itemNew.setProduct(b.getItemBaskets().get(i).getProduct());
					itemNew.setQuantity(b.getItemBaskets().get(i).getQuantity());
					itemNew.setSize(b.getItemBaskets().get(i).getSize());
					this.itemBasketService.save(itemNew); 
					items.add(itemNew);
				}
			}
//			Basket b1 = new Basket();
//			b1.setItemBaskets(items);
//			this.basketService.save(b1);
			// this.factureRepository.flush();
			facture1.setItemBaskets(items);
			facture1.setCreateDate(new Date());
			facture1.setStatus("PENDIENTE DE PAGO");
			facture1.setClient(null);
			this.factureRepository.save(facture1);
			factures.add(facture1);

		}
		b.setItemBaskets(new ArrayList<>());
		this.basketService.save(b);
		return factures;

	}

	@Transactional
	public Facture saveFactureUnify(Facture facture, String username) {
		Client c = this.clientService.findByUsername(username);
		List<ItemBasket> items = c.getBasket().getItemBaskets();
		List<ItemBasket> newItems = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
//			Product product= new Product();
//			product.setBrand(items.get(i).getProduct().getBrand());
//			product.setCapacity(items.get(i).getProduct().getCapacity());
//			product.setCategory(items.get(i).getProduct().getCategory());
//			product.setCreateDate(items.get(i).getProduct().getCreateDate());
//			product.setDescription(items.get(i).getProduct().getDescription());
//			product.setHeight(items.get(i).getProduct().getHeight());
//			product.setInch(items.get(i).getProduct().getInch());
//			product.setName(items.get(i).getProduct().getName());
//			product.setOffert(items.get(i).getProduct().getOffert());
//			product.setPhoto(items.get(i).getProduct().getPhoto());
//			product.setPrice(items.get(i).getProduct().getPrice());
//			product.setSize(items.get(i).getProduct().getSize());
//			product.setWeight(items.get(i).getProduct().getWeight());
//			product.setWidth(items.get(i).getProduct().getWidth());
//			product.setMemory(items.get(i).getProduct().getMemory());
//			product.setCompany(null);
//			this.productService.saveProduct(product);
//			items.get(i).setProduct(product);
			this.itemBasketService.save(items.get(i));
			newItems.add(items.get(i));
		}

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
	public Facture saveUpdateClient(Facture facture) {
		facture.setStatus("PAGADO");
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
