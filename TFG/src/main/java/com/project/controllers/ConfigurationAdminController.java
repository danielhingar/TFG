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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.project.domain.Configuration;

import com.project.services.ConfigurationService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/admin/configuration")
public class ConfigurationAdminController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ConfigurationService configurationService;

	// -------------------------- show configuration-----------------------------------
	
	@CrossOrigin
	@GetMapping("/show")
	public ResponseEntity<?> show() {
		Configuration configuration = null;
		Map<String, Object> response = new HashMap<>();
		try {
			configuration = configurationService.findOne();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (configuration == null) {
			response.put("mensaje", "La configuración no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Configuration>(configuration, HttpStatus.OK);
	}

	// --------------------------------Update shipping------------------------
	@Secured({"ROLE_ADMIN"})
	@CrossOrigin
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody Configuration configuration, BindingResult bindingResult) {
		Configuration configurationActually = this.configurationService.findOne();
		Configuration configurationUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (configurationActually == null) {
			response.put("mensaje", "Error: no se pudo editar la configuración no existe ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			configurationActually.setEmail(configuration.getEmail());
			configurationActually.setPhone(configuration.getPhone());
			configurationActually.setFacebook(configuration.getFacebook());
			configurationActually.setInstagram(configuration.getInstagram());
			configurationActually.setTwitter(configuration.getTwitter());
			configurationActually.setNameSystem(configuration.getNameSystem());
			configurationActually.setAddress(configuration.getAddress());
			configurationActually.setFailSystem(configuration.getFailSystem());
			configurationActually.setBanner(configuration.getBanner());
			
			configurationUpdated = this.configurationService.save(configurationActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La configuración ha sido actualizado");
		response.put("configuration", configurationUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//----------------------Stadistic------------------------------------------------------------
	@Secured({"ROLE_ADMIN"})
	@CrossOrigin
	@RequestMapping(value = "/statistics/productByCompany", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String,Integer> productByCompany() {
		Map<String,Integer> estadistica= new HashMap<>();
		
			estadistica = configurationService.productByCompany();
		
		return estadistica;
	}
	
	@Secured({"ROLE_ADMIN"})
	@CrossOrigin
	@RequestMapping(value = "/statistics/productByClient", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> productByClient() {
		Map<String,Integer> estadistica= new HashMap<>();
		Map<String, Object> response = new HashMap<>();
		try {  
			estadistica = configurationService.productByClient();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Integer>>( estadistica, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@CrossOrigin
	@RequestMapping(value = "/statistics/productSoldByCompany", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> productSoldByCompany() {
		Map<String,Integer> estadistica= new HashMap<>();
		Map<String, Object> response = new HashMap<>();
		try {  
			estadistica = configurationService.productSoldByCompany();
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Integer>>( estadistica, HttpStatus.OK);
	}
  
}
