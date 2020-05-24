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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Message;
import com.project.services.MessageService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/client/message")
public class MessageClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private MessageService messageService;

	// -------------------------- List messages by conversation
	// ----------------------------------
	@Secured({"ROLE_COMPANY","ROLE_CLIENT"})
	@CrossOrigin
	@RequestMapping(value = "/page/{page}/{idConversation}", method = RequestMethod.GET)
	public ResponseEntity<?> findMessagesByConversation(@PathVariable int idConversation, @PathVariable Integer page) {
		Page<Message> messages;
		Map<String, Object> response = new HashMap<>();
		try {
			messages = messageService.findMessageByConversation(idConversation,
					PageRequest.of(page, 1000000, org.springframework.data.domain.Sort.by("createDate").ascending()));
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Message>>(messages, HttpStatus.OK);
	}
	
	
	// ------------------------Create a message--------------------------------
	@Secured({ "ROLE_CLIENT" })
	@PostMapping("/create/{idConversation}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Message message, BindingResult bindingResult,
			@PathVariable int idConversation) {
		Message messageNew = null;
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
 
			messageNew = this.messageService.save(message, idConversation);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir el nuevo mensaje");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El mensaje ha sido creado con éxito");
		response.put("message", messageNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
