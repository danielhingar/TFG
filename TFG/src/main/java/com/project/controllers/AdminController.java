package com.project.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.project.domain.Admin;
import com.project.domain.Reporter;
import com.project.services.AdminService;
import com.project.services.ReporterService;


@RestController
@RequestMapping("/admin")
public class AdminController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ReporterService reporterService;

	// -------------------------- List Admin ----------------------------------
	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Admin> list() {
		return adminService.findAll();
	}

	// -------------------------- Show Admin ----------------------------------
	@CrossOrigin
	@RequestMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Admin admin = null;
		Map<String, Object> response = new HashMap<>();
		try {
			admin = adminService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (admin == null) {
			response.put("mensaje", "El admin no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}
	// -----------------------------------------LIST REPORTERS------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/listReporters", method = RequestMethod.GET)
	public List<Reporter> listReporters() {
		return reporterService.findAll();
	}

}
