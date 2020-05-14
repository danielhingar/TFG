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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Claim;
import com.project.domain.Conversation;
import com.project.services.ConversationService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/client/conversation")
public class ConversationClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ConversationService conversationService;

	// -------------------------- List conversation by client
	// ----------------------------------
	@Secured({ "ROLE_CLIENT" })
	@CrossOrigin
	@RequestMapping(value = "/myConversations/page/{page}/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> findConversationByClient(@PathVariable String username, @PathVariable Integer page) {
		Page<Conversation> conversations;
		Map<String, Object> response = new HashMap<>();
		try {
			conversations = conversationService.findConversationByClient(username,
					PageRequest.of(page, 9, org.springframework.data.domain.Sort.by("createDate").descending()));
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Conversation>>(conversations, HttpStatus.OK);
	}

	// ------------------------Create a conversation--------------------------------
	@Secured({ "ROLE_REPORTER" })
	@PostMapping("/create/{userClient}/{userCompany}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Conversation conversation, BindingResult bindingResult,
			@PathVariable String userClient, @PathVariable String userCompany) {
		Conversation conversationNew = null;
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

			conversationNew = this.conversationService.save(conversation, userClient, userCompany);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir la nueva conversación");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La conversación ha sido creado con éxito");
		response.put("conversation", conversationNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// -------------------------- Show Claim ----------------------------------
	@Secured({ "ROLE_CLIENT", "ROLE_COMPANY" })
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Conversation conversation = null;
		Map<String, Object> response = new HashMap<>();
		try {
			conversation = conversationService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (conversation == null) {
			response.put("mensaje", "La conversación no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conversation>(conversation, HttpStatus.OK);
	}

	// ---------------------------------Delete claim-----------------------
	
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			claimService.delete(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al borrar la queja");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La queja ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
