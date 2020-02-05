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

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Basket;
import com.project.domain.Client;
import com.project.domain.ItemBasket;
import com.project.services.BasketService;
import com.project.services.ClientService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/client/basket")
public class BasketClientController {

	// Servicies----------------------------------------------------------------------------------------------
	@Autowired
	private BasketService basketService;

	@Autowired
	private ClientService clientService;

	// ------------------Show itemBasket--------------
	@CrossOrigin
	@RequestMapping(value = "/show/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> showBasketClient(@PathVariable String username) {
		Basket basket = null;
		Map<String, Object> response = new HashMap<>();
		try {
			basket = basketService.findByClient(username);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Client c = clientService.findByUsername(username);
		if (c.getBasket() == null) {
			response.put("mensaje", "This client hasn't basket");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ItemBasket>>(basket.getItemBaskets(), HttpStatus.OK);
	}

	// ------------------Show itemBasket--------------
	@CrossOrigin
	@RequestMapping(value = "/showBasket/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> showBasket(@PathVariable String username) {
		Basket basket = null;
		Map<String, Object> response = new HashMap<>();
		try {
			basket = basketService.findByClient(username);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Client c = clientService.findByUsername(username);
		if (c.getBasket() == null) {
			response.put("mensaje", "This client hasn't basket");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Basket>(basket, HttpStatus.OK);
	}

	// --------------------------------Update basket adding a
	// product------------------------
	@CrossOrigin
	@PutMapping("/update/{username}/{productId}/{size}/{capacity}")
	public ResponseEntity<?> update(@Valid @RequestBody Basket basket, BindingResult bindingResult,
			@PathVariable int productId, @PathVariable String username, @PathVariable String size, @PathVariable String capacity) {
		Basket basketActually = this.basketService.findByClient(username);
		Basket basketUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (basketActually == null) {
			response.put("mensaje", "Error: no se pudo editar el carrito, no existe ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			basketUpdated = this.basketService.save(basketActually, productId, size,capacity);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El carrito ha sido actualizado");
		response.put("basket", basketUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
