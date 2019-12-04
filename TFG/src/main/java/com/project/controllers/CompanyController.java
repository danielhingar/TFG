package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Company;
import com.project.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private CompanyService companyService;

	// -------------------------- List ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Company> list() {
		return companyService.findAllRandom();
	}

}
