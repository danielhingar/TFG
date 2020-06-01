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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Client;
import com.project.domain.Usuario;
import com.project.services.ClientService;
import com.project.services.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/client")
public class ClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ClientService clientService;

	@Autowired
	private UsuarioService usuarioService;

	// -------------------------- Show ----------------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@GetMapping("/{username}")
	public ResponseEntity<?> show(@PathVariable String username) {
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		try {
			client = clientService.findByUsername(username);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (client == null) {
			response.put("mensaje", "El cliente no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	// -----------------------------Create
	// Client-------------------------------------------------
	
	@CrossOrigin
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult bindingResult) {
		Map<String, Object> response = new HashMap<>();

		List<String> usernames = new ArrayList<String>();
		List<Usuario> users = this.usuarioService.findAll();

		for (int i = 0; i < users.size(); i++) {
			usernames.add(users.get(i).getUsername());
		}

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("\nEl campo " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (usernames.contains(client.getUsername())) {
			response.put("mensaje", "No puede usar ese usuario");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}

		try {
			
			clientService.save(client);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente", client);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// --------------------------------Update client------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult bindingResult,
			@PathVariable int id) {
		Client clientActually = this.clientService.findById(id);
		Client clientUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (clientActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje", "Error: no se pudo editar el perfil,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clientActually.setCodePostal(client.getCodePostal());
			clientActually.setLocality(client.getLocality());
			clientActually.setProvince(client.getProvince());
			clientActually.setNumber(client.getNumber());
			clientActually.setAddress(client.getAddress());
			clientActually.setEmail(client.getEmail());
			clientActually.setName(client.getName());
			clientActually.setPhone(client.getPhone());
			clientActually.setSurnames(client.getSurnames());

			clientUpdated = this.clientService.update(clientActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El perfil ha sido actualizada");
		response.put("client", clientUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	// --------------------------------add product wish------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@PutMapping("/addWish/{username}/{id}")
	public ResponseEntity<?> addWish(@PathVariable int id, @PathVariable String username) {
		Client clientActually = this.clientService.findByUsername(username);
		Map<String, Object> response = new HashMap<>();

		try {
			this.clientService.addProductToWish(clientActually, id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se ha añadido el producto a la lista de deseados");
		response.put("client", clientActually);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	// --------------------------------add product wish------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@DeleteMapping("/removeWish/{username}/{id}")
	public ResponseEntity<?> removeWish(@PathVariable int id, @PathVariable String username) {
		Client clientActually = this.clientService.findByUsername(username);
		Map<String, Object> response = new HashMap<>();

		try {
			this.clientService.removeProductToWish(clientActually, id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se ha eliminado el producto a la lista de deseados");
		response.put("client", clientActually);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
