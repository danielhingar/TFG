package com.project.controllers;


import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Product;
import com.project.services.ProductService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/product")
public class ProductController {

	// Servicies----------------------------------------------------------------------------------------------
	@Autowired
	private ProductService productService;
	
//	@Autowired
//	private CompanyService companyService;
 
	// -------------------------------------Methods-------------------------------------------------------------

//	// -------------------------- List all products ----------------------------------
//	@CrossOrigin
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public List<Product> list() {
//		return productService.findAll();
//	}
	
	@CrossOrigin
	@RequestMapping(value = "/list/page/{page}/{username}", method = RequestMethod.GET)
	public Page<Product> list(@PathVariable Integer page,@PathVariable String username ) {
		return productService.findAllByCompany(username,PageRequest.of(page, 9));
	}

	// --------------------------List product by company-------------------------------------------------
//	@CrossOrigin
//	@RequestMapping(value = "/list/{username}", method = RequestMethod.GET)
//	public ResponseEntity<?> listProductsCompany(@PathVariable String username) {
//		List<Product> product = new ArrayList<Product>();
//		Map<String, Object> response = new HashMap<>();
//		try {
//			product = productService.findAllByCompany(username);
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al realizar la consulta en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		Company c=companyService.findByUsername(username);
//		if (c.getProducts() == null) {
//			response.put("mensaje", "This company hasn't products");
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<List<Product>>( product, HttpStatus.OK);
//	}
//	
//	

	// -------------------------- Show detail of a product----------------------------------
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
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
}
