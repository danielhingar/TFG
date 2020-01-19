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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
