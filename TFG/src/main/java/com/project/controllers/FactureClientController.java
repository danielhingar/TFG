package com.project.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Facture;
import com.project.services.FactureService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/client/facture")
public class FactureClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private FactureService factureService;

	// -------------------------- List Facture by Client
	// ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/myFactures/{clientId}", method = RequestMethod.GET)
	public List<Facture> listFactureByClient(@PathVariable int clientId) {
		return factureService.findFactureByClient(clientId);
	}

	// ---------------------------- Show
	// facture----------------------------------------------------------
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

	// ------------------------Create a factures of the
	// basket-----------------------------------
	@PostMapping("/create/{idBasket}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Facture facture, BindingResult bindingResult,
			@PathVariable String username) {
		List<Facture> facturesNew = null;
		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {

			facturesNew = this.factureService.save(facture, username);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir la nueva factura");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Las facturas han sido creadas con éxito");
		response.put("facture", facturesNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// --------------------------------Update shipping------------------------
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Facture facture, BindingResult bindingResult,
			@PathVariable int id) {
		Facture factureActually = this.factureService.findById(id);
		Facture factureUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (factureActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje",
					"Error: no se pudo editar la factura,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			factureActually.setAddress(facture.getAddress());
			factureActually.setLocality(facture.getLocality());
			factureActually.setName(facture.getName());
			factureActually.setNumber(facture.getNumber());
			factureActually.setPhone(facture.getPhone());
			factureActually.setPostalCode(facture.getPostalCode());
			factureActually.setProvince(facture.getProvince());
			factureActually.setSurnames(facture.getSurnames());
			factureActually.setStatus(facture.getStatus());
			
			
			factureUpdated = this.factureService.save(factureActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La factura ha sido actualizada");
		response.put("facture", factureUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// ---------------------------------Delete item-----------------------
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			this.factureService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al borrar la factura");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La factura ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
