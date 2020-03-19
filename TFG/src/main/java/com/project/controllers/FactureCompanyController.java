package com.project.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Facture;
import com.project.services.FactureService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/company/facture")
public class FactureCompanyController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private FactureService factureService; 

	// -------------------------- List Facture by Client
	// ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/myFactures/page/{page}/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> listFactureByCompany(@PathVariable String username,@PathVariable Integer page) {
		Page<Facture> factures;
		Map<String, Object> response = new HashMap<>();
		try {
			factures = factureService.findFactureByCompany(username,PageRequest.of(page, 9)); 
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Facture>>( factures, HttpStatus.OK);
	}
	

	// ---------------------------- Show facture----------------------------------------------------------
	@CrossOrigin
	@RequestMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Facture facture = null;
		Map<String, Object> response = new HashMap<>();
		try {
			facture = factureService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (facture == null) {
			response.put("mensaje", "La factura no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Facture>(facture, HttpStatus.OK);
	}
}
