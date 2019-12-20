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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.project.domain.Claim;
import com.project.services.ClaimService;

@RestController
@RequestMapping("/reporter/claim")
public class ClaimReporterController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ClaimService claimService;

	// -------------------------- List Admin ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/listMyClaims", method = RequestMethod.GET)
	public List<Claim> listMyClaims() {
		// final Usuario
		// a=this.usuarioService.findByUsername(UsuarioService.getPrincipal());
		// final int reporterId=a.getId();
		return claimService.findClaimByReporter(998);
	}

	// -------------------------- List Admin ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Claim> list() {
		return claimService.findAll();
	}

	// -------------------------- Show Claim ----------------------------------
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
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

	// --------------------------------Update shipping------------------------
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Claim claim, BindingResult bindingResult,
			@PathVariable int id) {
		Claim claimActually = this.claimService.findById(id);
		Claim claimUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (claimActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje", "Error: no se pudo editar la queja,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			claimActually.setStatus(claim.getStatus());
			claimActually.setAnswer(claim.getAnswer());

			claimUpdated = this.claimService.saveClaim(claimActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La queja ha sido actualizada");
		response.put("claim", claimUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// --------------------------------Update shipping------------------------
	@CrossOrigin
	@PutMapping("/assign/{id}")
	public ResponseEntity<?> assign(@Valid @RequestBody Claim claim, BindingResult bindingResult,
			@PathVariable int id) {
		Claim claimActually = this.claimService.findById(id);
		Claim claimUpdated = null;
		
		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (claimActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje", "Error: no se pudo editar la queja,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			claimUpdated = this.claimService.assign(claimActually,1);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La queja ha sido actualizada");
		response.put("claim", claimUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
