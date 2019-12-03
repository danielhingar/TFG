package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Facture;
import com.project.services.FactureService;

@RestController
@RequestMapping("/company")
public class FactureCompanyController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private FactureService factureService;

	// -------------------------- List Facture by Client ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/myFactures/{companyId}", method = RequestMethod.GET)
	public List<Facture> listFactureByCompany(@PathVariable Long companyId) {
		return factureService.findFactureByCompany(companyId);
	}
}
