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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Comment;
import com.project.services.CommentService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/comment")
public class CommentController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private CommentService commentService;

	// -------------------------- Show Claim ----------------------------------
	@Secured({ "ROLE_CLIENT"})
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Comment comment = null;
		Map<String, Object> response = new HashMap<>();
		try {
			comment = commentService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	// -------------------------- List claim by client -------------------------
	@CrossOrigin
	@RequestMapping(value = "/condition/{username}/{commentId}", method = RequestMethod.GET)
	public Boolean deleteCondition(@PathVariable String username,@PathVariable int commentId) {
		return this.commentService.deleteCondition(commentId, username);
		
	}

	// -------------------------- List comment by product
	// ----------------------------------
	
	@CrossOrigin
	@RequestMapping(value = "/product/page/{page}/{productId}", method = RequestMethod.GET)
	public ResponseEntity<?> findCommentByProduct(@PathVariable int productId, @PathVariable Integer page) {
		Page<Comment> comments;
		Map<String, Object> response = new HashMap<>();
		try {
			comments = commentService.findCommentByProduct(productId, PageRequest.of(page, 9));
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Comment>>(comments, HttpStatus.OK);
	}

	// ------------------------Create a claim--------------------------------
	@Secured({ "ROLE_CLIENT" })
	@PostMapping("/create/{idProduct}/{username}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Comment comment, BindingResult bindingResult,
			@PathVariable int idProduct, @PathVariable String username) {
		Comment commentNew = null;
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

			commentNew = this.commentService.save(comment, idProduct, username);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir la nueva queja");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La queja ha sido creado con éxito");
		response.put("comment", commentNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	
	// ---------------------------------Delete claim-----------------------
	@Secured({ "ROLE_CLIENT" })
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			commentService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al borrar el comentario");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El comentario ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
