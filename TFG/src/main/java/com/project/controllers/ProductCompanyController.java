package com.project.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.Product;
import com.project.services.ProductService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/company/product")
public class ProductCompanyController {

	// Servicies----------------------------------------------------------------------------------------------
	@Autowired
	private ProductService productService;

	//List
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@RequestMapping(value = "/list/page/{page}/{username}", method = RequestMethod.GET)
	public Page<Product> list(@PathVariable Integer page, @PathVariable String username) {
		return productService.findAllByCompanyClient(username, PageRequest.of(page, 9,org.springframework.data.domain.Sort.by("createDate").descending()));
	}


	// -------------------------------------Create a
	// product----------------------------------------------------
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@PostMapping("/create/{username}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Product product,@PathVariable String username, BindingResult bindingResult) {
		Product productNew = null;
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

			productNew = productService.saveProduct(product,username);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir el nuevo producto");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido creado con éxito");
		response.put("product", productNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// -------------------------------------Update a
	// product----------------------------------------------------
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult bindingResult,
			@PathVariable int id) {
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
			Integer id1 = (Integer) id;
			response.put("mensaje",
					"Error: no se pudo editar el producto,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			productActually.setBrand(product.getBrand());
			productActually.setCapacity(product.getCapacity());
			productActually.setCategory(product.getCategory());
			productActually.setCreateDate(product.getCreateDate());
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
			productActually.setMemory(product.getMemory());

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

	// -------------------------------------Delete a
	// product----------------------------------------------------
	@Secured({"ROLE_COMPANY"})
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Product product = productService.findById(id);
			String nombreFotoAnterior = product.getPhoto();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
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
	
	@Secured({"ROLE_COMPANY"})
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") int id) {
		Map<String, Object> response = new HashMap<>();

		Product product = productService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del producto " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = product.getPhoto();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			product.setPhoto(nombreArchivo);

			productService.saveProduct(product);

			response.put("product", product);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		
		Resource recurso= null;
		
		try {
			recurso= new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		HttpHeaders cabecera= new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
}
