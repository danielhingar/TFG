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
import org.springframework.web.bind.annotation.RestController;
import com.project.domain.Client;
import com.project.domain.Usuario;
import com.project.services.ClientService;
import com.project.services.UsuarioService;


@RestController
@RequestMapping("/client")
public class ClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UsuarioService usuarioService;


	// -------------------------- Show ----------------------------------
	@CrossOrigin
	@RequestMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		try {
			client = clientService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (client == null) {
			response.put("mensaje", "El admin no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	//-----------------------------Create Client-------------------------------------------------
		@CrossOrigin
		@PostMapping("/create")
		public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult bindingResult) {
			Client clientNew = null;
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
			
			if(usernames.contains(client.getUsername())) {
				response.put("mensaje", "No puede usar ese username");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			try {
				clientNew = clientService.save(client);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "El cliente ha sido creado con éxito");
			response.put("cliente", clientNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		}
}
