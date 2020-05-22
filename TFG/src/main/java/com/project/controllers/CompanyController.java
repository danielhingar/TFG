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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


import com.project.domain.Company;

import com.project.domain.Usuario;
import com.project.services.CompanyService;
import com.project.services.UsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/company")
public class CompanyController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private CompanyService companyService;

	@Autowired
	private UsuarioService usuarioService;

	//private final Logger log = LoggerFactory.getLogger(CompanyController.class);

	// -------------------------- Show ----------------------------------
	@Secured({"ROLE_COMPANY","ROLE_CLIENT","ROLE_ADMIN","ROLE_REPORTER"})
	@CrossOrigin
	@GetMapping("/{username}")
	public ResponseEntity<?> show(@PathVariable String username) {
		Company company = null;
		Map<String, Object> response = new HashMap<>();
		try {
			company = companyService.findByUsername(username);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (company == null) {
			response.put("mensaje", "La compañía no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	//----------------------List-----------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Company> list() {
		return companyService.findAll();
	}
	
	// -------------------------- Show ----------------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Company company = null;
		Map<String, Object> response = new HashMap<>();
		try {
			company = companyService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (company == null) {
			response.put("mensaje", "La compañía no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}



	// -------------------------- List ----------------------------------

	@CrossOrigin
	@RequestMapping(value = "/list/page/{page}", method = RequestMethod.GET)
	public Page<Company> list(@PathVariable Integer page) {
		return companyService.findAll(PageRequest.of(page, 6,org.springframework.data.domain.Sort.by("createDate").descending()));
	}

	// -----------------------------Create
	// Company-------------------------------------------------
	@Secured({"ROLE_ADMIN"})
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

		if (usernames.contains(company.getUsername())) {
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

	// --------------------------------Update company------------------------
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Company company, BindingResult bindingResult,
			@PathVariable int id) {
		Company companyActually = this.companyService.findById(id);
		Company companyUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (companyActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje", "Error: no se pudo editar el perfil,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			companyActually.setEmail(company.getEmail());
			companyActually.setName(company.getName());
			companyActually.setPhone(company.getPhone());
			companyActually.setSurnames(company.getSurnames());
			companyActually.setBusinessName(company.getBusinessName());
			companyActually.setCategory(company.getCategory());
			companyActually.setImage(company.getImage());
			companyUpdated = this.companyService.update(companyActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El perfil ha sido actualizada");
		response.put("company", companyUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//----------------------Stadistic------------------------------------------------------------
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@GetMapping("/statistics/productByCategory/{username}")
	public Map<String,Integer> productByCompany(@PathVariable String username) {
		Map<String,Integer> estadistica= new HashMap<>();
		
			estadistica = companyService.productByCategory(username);
		
		return estadistica;
	}
	
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@GetMapping("/statistics/productSold/{username}")
	public Map<String,Integer> productSold(@PathVariable String username) {
		Map<String,Integer> estadistica= new HashMap<>();
		
			estadistica = companyService.productSoldByCompany(username);
		
		return estadistica;
	}
	
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@GetMapping("/statistics/productOffert/{username}")
	public Map<String,Integer> productInOffert(@PathVariable String username) {
		Map<String,Integer> estadistica= new HashMap<>();
		
			estadistica = companyService.productInOffert(username);
		
		return estadistica;
	}



}
