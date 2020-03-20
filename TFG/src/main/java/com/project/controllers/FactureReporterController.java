package com.project.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Facture;

import com.project.services.FactureService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/reporter/facture")
public class FactureReporterController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private FactureService factureService;

	// -------------------------- List Facture by Client
	// ----------------------------------
	@Secured({ "ROLE_REPORTER" })
	@CrossOrigin
	@RequestMapping(value = "/all/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<?> findFacturesAllClients(@PathVariable Integer page) {
		Page<Facture> factures;
		Map<String, Object> response = new HashMap<>();
		try {
			factures = factureService.findFacturesAllClients(PageRequest.of(page, 9));
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Facture>>(factures, HttpStatus.OK);
	}

	// -------------------------- List Facture by Client
	// ----------------------------------
	@Secured({ "ROLE_REPORTER" })
	@CrossOrigin
	@RequestMapping(value = "/allCompany/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<?> findFacturesAllCompany(@PathVariable Integer page) {
		Page<Facture> factures;
		Map<String, Object> response = new HashMap<>();
		try {
			factures = factureService.findFactureAllCompany(PageRequest.of(page, 9));
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Facture>>(factures, HttpStatus.OK);
	}

	// --------------------------------Update facture------------------------
	@Secured({ "ROLE_REPORTER" })
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

	// --------------------------------pay facture company------------------------
	@Secured({"ROLE_REPORTER"})
	@CrossOrigin
	@PutMapping("/payCompany/{id}")
	public ResponseEntity<?> enable(@PathVariable int id) {
		Facture factureActually = this.factureService.findById(id);
		Map<String, Object> response = new HashMap<>();

		try {
			factureActually.setStatus("PAGADA");
			this.factureService.save(factureActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Has pagado a la compañía");
		response.put("facture", factureActually);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}