package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	// ------------------------------------------CRUD------------------------------------------------------

	// ------------------------------------------LIST--------------------------------------------------------------

	// -------------------------- List Admin ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Shipping> list() {
		return shippingService.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/show/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Shipping show(@PathVariable Long id) {
		final Shipping shipping;
		
		shipping = shippingService.findById(id);
		
		return shipping ;
	}

}
