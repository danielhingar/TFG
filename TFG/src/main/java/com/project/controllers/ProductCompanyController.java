package com.project.controllers;


import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Company;
import com.project.domain.Product;
import com.project.services.CompanyService;
import com.project.services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductCompanyController {

	// Servicies----------------------------------------------------------------------------------------------
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CompanyService companyService;

	// -------------------------- List ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Product> list() {
		return productService.findAll();
	}

	// -------------------------- Show ----------------------------------
	@CrossOrigin
	@RequestMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Product product = null;
		Map<String, Object> response = new HashMap<>();
		try {
			product = productService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (product == null) {
			response.put("mensaje", "El producto no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	// -------------------------------------Create----------------------------------------------------
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> crear(@Valid @RequestBody Product product, BindingResult bindingResult) {
		Product productNew = null;
		Map<String, Object> response = new HashMap<>();
		Long idCompany = 1L;
		Company company=companyService.findById(idCompany);

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			product.setCreateDate(new Date());
			product.setCompany(company);
			productNew = productService.saveProduct(product);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir el nuevo producto");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido creado con éxito");
		response.put("product", productNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult bindingResult,
			@PathVariable Long id) {
		Product productActually = productService.findById(id);
		Product productUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (productActually == null) {
			response.put("mensaje",
					"Error: no se pudo editar el producto,".concat(id.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			productActually.setBrand(product.getBrand());
			productActually.setCapacity(product.getCapacity());
			productActually.setCategory(product.getCategory());
			productActually.setCreateDate(new Date());
			productActually.setDescription(product.getDescription());
			productActually.setHeight(product.getHeight());
			productActually.setInch(product.getInch());
			productActually.setName(product.getName());
			productActually.setOffert(product.getOffert());
			productActually.setPhoto(product.getPhoto());
			productActually.setPrice(product.getPrice());
			productActually.setSize(product.getSize());
			productActually.setWeight(product.getWeight());
			productActually.setWidth(product.getWidth());

			productUpdated = productService.saveProduct(productActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido actualizado");
		response.put("producto", productUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
//			Product product = productService.findById(id);
//			String nombreFotoAnterior = cliente.getFoto();
//
//			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
//				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
//				File archivoFotoAnterior = rutaFotoAnterior.toFile();
//				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
//					archivoFotoAnterior.delete();
//				}
//			}
			productService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al borrar el producto");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}