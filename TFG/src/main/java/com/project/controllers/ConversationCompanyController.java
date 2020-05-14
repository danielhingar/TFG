package com.project.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Claim;
import com.project.domain.Conversation;
import com.project.services.ConversationService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/company")
public class ConversationCompanyController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ConversationService conversationService;
	
	
	// -------------------------- List claim by client
	// ----------------------------------
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@RequestMapping(value = "/myConversations/page/{page}/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> findClaimByClient(@PathVariable String username,@PathVariable Integer page) {
		Page<Conversation> conversations;
		Map<String, Object> response = new HashMap<>();
		try {
			conversations = conversationService.findConversationByCompany(username,PageRequest.of(page, 9,org.springframework.data.domain.Sort.by("createDate").descending()));
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Conversation>>( conversations, HttpStatus.OK);
	}

}
