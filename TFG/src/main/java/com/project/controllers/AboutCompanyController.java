package com.project.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.About;
import com.project.services.AboutService;

@RestController
@RequestMapping("/company/about")
public class AboutCompanyController {

	// Servicies----------------------------------------------------------------------------------------------
	@Autowired
	private AboutService aboutService;

	// ------------------Show about--------------
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		About about = null;
		Map<String, Object> response = new HashMap<>();
		try {
			about = aboutService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (about == null) {
			response.put("mensaje", "El about no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<About>(about, HttpStatus.OK);
	}

	// --------------------------------Update about------
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody About about, BindingResult bindingResult,
			@PathVariable int id) {
		About aboutActually = this.aboutService.findById(id);
		About aboutUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (aboutActually == null) {
			response.put("mensaje", "Error: no se pudo editar el carrito, no existe ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			aboutActually.setAddress(about.getAddress());
			aboutActually.setDescription(about.getDescription());
			aboutActually.setFacebook(about.getFacebook());
			aboutActually.setImages(about.getImages());
			aboutActually.setInstagram(about.getInstagram());

			aboutUpdated = this.aboutService.save(aboutActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El about ha sido actualizado");
		response.put("about", aboutUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") List<MultipartFile> archivo, @RequestParam("id") int id) {
		Map<String, Object> response = new HashMap<>();

		About about = aboutService.findById(id);
		String images = "";
		for (MultipartFile file : archivo)
			if (!archivo.isEmpty()) {
				String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
				Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
				try {
					Files.copy(file.getInputStream(), rutaArchivo);
					images = images + "," + nombreArchivo;
				} catch (IOException e) {
					response.put("mensaje", "Error al subir las imagenes del producto " + nombreArchivo);
					response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

//			String nombreFotoAnterior = company.getImage();

//			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
//				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
//				File archivoFotoAnterior = rutaFotoAnterior.toFile();
//				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
//					archivoFotoAnterior.delete();
//				}
//			}
				images.replaceFirst(images, "");
				about.setImages(images);

				aboutService.save(about);

				response.put("about", about);
				response.put("mensaje", "Has subido correctamente las imagenes: " + images);
			}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
