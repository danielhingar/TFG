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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Facture;
import com.project.domain.ItemBasket;
import com.project.domain.Product;
import com.project.services.ItemBasketService;
import com.project.services.ProductService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/client/itemBasket")
public class ItemBasketClientController {

	// Servicies----------------------------------------------------------------------------------------------
	@Autowired
	private ItemBasketService itemBasketService;
	
	@Autowired
	private ProductService productService;

	// ------------------Show item--------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		ItemBasket itemBasket = null;
		Map<String, Object> response = new HashMap<>();
		try {
			itemBasket = itemBasketService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (itemBasket == null) {
			response.put("mensaje", "El item no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ItemBasket>(itemBasket, HttpStatus.OK);
	}

	// ------------------------Create a item of the
	// basket-----------------------------------
	@Secured({"ROLE_CLIENT"})
	@PostMapping("/create/{idProduct}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody ItemBasket itemBasket, BindingResult bindingResult,
			@PathVariable int idProduct) {
		ItemBasket itemBasketNew = null;
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

			itemBasketNew = this.itemBasketService.save(itemBasket, idProduct);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir el nuevo item");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El item ha sido creado con éxito");
		response.put("itemBasket", itemBasketNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// --------------------------------Update item------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ItemBasket itemBasket, BindingResult bindingResult,
			@PathVariable int id) {
		ItemBasket itemBasketActually = this.itemBasketService.findById(id);
		ItemBasket itemBasketUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (itemBasketActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje", "Error: no se pudo editar el item,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			itemBasketActually.setQuantity(itemBasket.getQuantity());
			
			itemBasketUpdated = this.itemBasketService.save(itemBasketActually);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El método de envío ha sido actualizado");
		response.put("itemBasket", itemBasketUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// --------------------------------Update facture------------------------
		@Secured({ "ROLE_REPORTER" })
		@CrossOrigin
		@PutMapping("/updateStatus/{id}")
		public ResponseEntity<?> updateStatus(@Valid @RequestBody ItemBasket itemBasket, BindingResult bindingResult,
				@PathVariable int id) {
			ItemBasket itemBasketActually = this.itemBasketService.findById(id);
			ItemBasket ItemBasketUpdated = null;

			Map<String, Object> response = new HashMap<>();

			if (bindingResult.hasErrors()) {
				List<String> errors = new ArrayList<String>();
				for (FieldError err : bindingResult.getFieldErrors()) {
					errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
				}
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}

			if (itemBasketActually == null) {
				Integer id1 = (Integer) id;
				response.put("mensaje",
						"Error: no se pudo editar la factura,".concat(id1.toString().concat(" no existe ")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			try {
				itemBasketActually.setStatus(itemBasket.getStatus());

				ItemBasketUpdated = this.itemBasketService.save(itemBasketActually);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "La factura ha sido actualizada");
			response.put("facture", ItemBasketUpdated);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

	// ---------------------------------Delete item-----------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			this.itemBasketService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al borrar el item");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El item ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
