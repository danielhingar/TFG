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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.project.domain.Admin;
import com.project.domain.Client;
import com.project.domain.Company;
import com.project.domain.Reporter;
import com.project.domain.Usuario;
import com.project.services.AdminService;
import com.project.services.ClientService;
import com.project.services.CompanyService;
import com.project.services.ReporterService;
import com.project.services.UsuarioService;


@RestController
@RequestMapping("/admin")
public class AdminController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private AdminService adminService;

	@Autowired
	private ReporterService reporterService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UsuarioService usuarioService;

	//-----------------------------Create Admin-------------------------------------------------
	@CrossOrigin
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody Admin admin, BindingResult bindingResult) {
		Admin adminNew = null;
		Map<String, Object> response = new HashMap<>();
		
		List<String> usernames=  new ArrayList<String>();
		List<Usuario> users=this.usuarioService.findAll();
		
		for(int i=0;i<users.size();i++) {
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
		
		if(usernames.contains(admin.getUsername())) {
			response.put("mensaje", "No puede usar ese username");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			adminNew = this.adminService.save(admin);
			
		

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El admin ha sido creado con éxito");
		response.put("admin", adminNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// -------------------------- List Admin ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Admin> list() {
		return adminService.findAll();
	}

	// -------------------------- Show Admin ----------------------------------
	@CrossOrigin
	@RequestMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Admin admin = null;
		Map<String, Object> response = new HashMap<>();
		try {
			admin = adminService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (admin == null) {
			response.put("mensaje", "El admin no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	// -----------------------------------------List all reporters------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/listReporters", method = RequestMethod.GET)
	public List<Reporter> listReporters() {
		return reporterService.findAll();
	}
	
	// -------------------------- List all clients----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/listClients", method = RequestMethod.GET)
	public List<Client> listClients() {
		return clientService.findAll();
	}

	// -------------------------- List all companies ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/listCompanies", method = RequestMethod.GET)
	public List<Company> listCompany() {
		return companyService.findAll();
	}
	
	//---------------------------- List users ------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/listUsuarios", method = RequestMethod.GET)
	public List<Usuario> listUsuarios() {
		return usuarioService.findAll();
	}

}
