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

import com.project.domain.Shipping;
import com.project.services.ShippingService;

@RestController
@RequestMapping("/admin/shipping")
public class ShippingAdminController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ShippingService shippingService;

	// -------------------------- List shipping ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Shipping> list() {
		return shippingService.findAll();
	}

	// ------------------Show shipping--------------
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Shipping shipping = null;
		Map<String, Object> response = new HashMap<>();
		try {
			shipping = shippingService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (shipping == null) {
			response.put("mensaje", "El métado de compra no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Shipping>(shipping, HttpStatus.OK);
	}
	

	// ------------------------Create
	// shipping----------------------------------------------
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Shipping shipping, BindingResult bindingResult) {
		Shipping shippingNew = null;
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

			shippingNew = shippingService.save(shipping);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir el nuevo método de pago");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El método de pago ha sido creado con éxito");
		response.put("shipping", shippingNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// --------------------------------Update shipping------------------------
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Shipping shipping, BindingResult bindingResult,
			@PathVariable int id) {
		Shipping shippingActually = shippingService.findById(id);
		Shipping shippingUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (shippingActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje",
					"Error: no se pudo editar el producto,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			shippingActually.setDateMax(shipping.getDateMax());
			shippingActually.setDateMin(shipping.getDateMin());
			shippingActually.setObservation(shipping.getObservation());
			shippingActually.setPrice(shipping.getPrice());
			shippingActually.setTitle(shipping.getTitle());

			shippingUpdated = shippingService.save(shippingActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El método de envío ha sido actualizado");
		response.put("shipping", shippingUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// ---------------------------------Delete shipping-----------------------
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			shippingService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al borrar el método de envío");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El método de envío ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
