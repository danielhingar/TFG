package com.project.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Client;
import com.project.domain.Company;
import com.project.domain.Configuration;
import com.project.domain.Facture;
import com.project.domain.ItemBasket;
import com.project.repositories.ConfigurationRepository;

@Service
@Transactional
public class ConfigurationService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private FactureService factureService;

	// Services----------------------------------------------------------------------------------------------------

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Configuration findOne() {
		List<Configuration> configurations= this.configurationRepository.findAll();
		return configurations.get(0);
	}
	
	//----------------------------------------Create ----------------------------------------------------
	@Transactional
	public Configuration save(Configuration configuration) {
		return configurationRepository.save(configuration);
	}
	
	// ----------------------------------------Stadistic------------------------------------------------------
	@Transactional(readOnly = true)
	public Map<String,Integer> productByCompany(){
		Map<String,Integer> res = new HashMap<>();
		List<Company> companies= this.companyService.findAll();
		for(Company c: companies) {
			res.put(c.getBusinessName(), c.getProducts().size());
		}
		return res;
	}
	
	@Transactional(readOnly = true)
	public Map<String,Integer> productByClient(){
		Map<String,Integer> res = new HashMap<>();
		List<Client> clients= this.clientService.findAll();
		for(Client c: clients) {
			Integer numProducts = 0;
			List<Facture> factures= factureService.findFacturesClient(c.getUsername());
			for(Facture f:factures) {
				List<ItemBasket> itemBaskets= f.getItemBaskets();
				for(ItemBasket b:itemBaskets) {
					numProducts= numProducts+ b.getQuantity();
				}
			}
			res.put(c.getUsername(), numProducts);
		}
		return res;
	}
	
	@Transactional(readOnly = true)
	public Map<String,Integer> productSoldByCompany(){
		Map<String,Integer> res = new HashMap<>();
		List<Company> companies= this.companyService.findAll();
		for(Company c: companies) {
			Integer numProducts = 0;
			List<Facture> factures= factureService.findFactureByCompany(c.getUsername());
			for(Facture f:factures) {
				List<ItemBasket> itemBaskets= f.getItemBaskets();
				for(ItemBasket b:itemBaskets) {
					numProducts= numProducts+ b.getQuantity();
				}
			}
			res.put(c.getBusinessName(), numProducts);
		}
		return res;
	}
	

}
