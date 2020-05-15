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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Message;
import com.project.services.MessageService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/company/message")
public class MessageCompanyController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private MessageService messageService;

	// ------------------------Create a message--------------------------------
	@Secured({ "ROLE_COMPANY" })
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

			messageNew = this.messageService.saveCompany(message, idConversation);

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
