package com.project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Admin;
import com.project.domain.Claim;
import com.project.services.ClaimService;

@RestController
@RequestMapping("/client")
public class ClaimClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ClaimService claimService;

	// -------------------------- List Admin ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/myClaims/{factureId}", method = RequestMethod.GET)
	public List<Claim> list(@PathVariable Long factureId) {
		return claimService.findClaimByFacture(factureId);
	}

	// -------------------------- Show Claim ----------------------------------
	@CrossOrigin
	@RequestMapping("/claim/show/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Claim claim = null;
		Map<String, Object> response = new HashMap<>();
		try {
			claim = claimService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (claim == null) {
			response.put("mensaje", "La queja no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Claim>(claim, HttpStatus.OK);
	}
}
