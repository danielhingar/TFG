package com.project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.project.domain.Facture;

import com.project.services.FactureService;

@RestController
@RequestMapping("/client/facture")
public class FactureClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private FactureService factureService;

	// -------------------------- List Facture by Client ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/myFactures/{clientId}", method = RequestMethod.GET)
	public List<Facture> listFactureByClient(@PathVariable int clientId) {
		return factureService.findFactureByClient(clientId);
	}  

	// ---------------------------- Show facture----------------------------------------------------------
	@CrossOrigin
	@RequestMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
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
