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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.project.domain.Company;
import com.project.domain.Usuario;
import com.project.services.CompanyService;
import com.project.services.UsuarioService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UsuarioService usuarioService;

	// -------------------------- List ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Company> list() {
		return companyService.findAllRandom();
	}

	// -----------------------------Create
	// Company-------------------------------------------------
	@CrossOrigin
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody Company company, BindingResult bindingResult) {
		Company companyNew = null;
		Map<String, Object> response = new HashMap<>();

		List<String> usernames = new ArrayList<String>();
		List<Usuario> users = this.usuarioService.findAll();

		for (int i = 0; i < users.size(); i++) {
			usernames.add(users.get(i).getUsername());
		}

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(usernames.contains(company.getUsername())) {
			response.put("mensaje", "No puede usar ese username");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			companyNew = companyService.save(company);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La compañía ha sido creado con éxito");
		response.put("company", companyNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
