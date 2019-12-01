package com.project.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.project.domain.Configuration;
import com.project.services.ConfigurationService;


@RestController
@RequestMapping("/admin/configuration")
public class ConfigurationAdminController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ConfigurationService configurationService;

	// -------------------------- show configuration ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Configuration show() {
		return configurationService.findOne();
	}

}
