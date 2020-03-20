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

import com.project.domain.Claim;
import com.project.services.ClaimService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/client/claim")
public class ClaimClientController {

	// Services--------------------------------------------------------------------------------------
	@Autowired
	private ClaimService claimService;

	// -------------------------- Show Claim ----------------------------------
	@Secured({"ROLE_CLIENT","ROLE_REPORTER"})
	@CrossOrigin
	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable int id) {
		Claim claim = null;
		Map<String, Object> response = new HashMap<>();
		try {
			claim = claimService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (claim == null) {
			response.put("mensaje", "La queja no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Claim>(claim, HttpStatus.OK);
	}

	// -------------------------- List claim by client -------------------------	
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@RequestMapping(value = "/claimByFacture/{factureId}", method = RequestMethod.GET)
	public ResponseEntity<?> findClaimByFacture(@PathVariable int factureId) {
		List<Claim> claims = new ArrayList<Claim>();
		Map<String, Object> response = new HashMap<>();
		try {
			claims = claimService.findClaimByFacture(factureId);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Claim>>( claims, HttpStatus.OK);
	}

	// -------------------------- List claim by client
	// ----------------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@RequestMapping(value = "/myClaims/page/{page}/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> findClaimByClient(@PathVariable String username,@PathVariable Integer page) {
		Page<Claim> claims;
		Map<String, Object> response = new HashMap<>();
		try {
			claims = claimService.findClaimByClient(username,PageRequest.of(page, 9));
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Claim>>( claims, HttpStatus.OK);
	}


	// ------------------------Create a claim--------------------------------
	@Secured({"ROLE_CLIENT"})
	@PostMapping("/create/{idFacture}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Claim claim, BindingResult bindingResult,
			@PathVariable int idFacture) {
		Claim claimNew = null;
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

			claimNew = this.claimService.save(claim, idFacture);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al añadir la nueva queja");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La queja ha sido creado con éxito");
		response.put("claim", claimNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// --------------------------------Update claim------------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Claim claim, BindingResult bindingResult,
			@PathVariable int id) {
		Claim claimActually = this.claimService.findById(id);
		Claim claimUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errors.add("El campo " + err.getField() + " " + err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (claimActually == null) {
			Integer id1 = (Integer) id;
			response.put("mensaje", "Error: no se pudo editar la queja,".concat(id1.toString().concat(" no existe ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			claimActually.setAttachment(claim.getAttachment());
			claimActually.setDescription(claim.getDescription());
			claimActually.setTitle(claim.getTitle());
			

			claimUpdated = this.claimService.saveClaim(claimActually);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La queja ha sido actualizado");
		response.put("claim", claimUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// ---------------------------------Delete claim-----------------------
	@Secured({"ROLE_CLIENT"})
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			claimService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al borrar la queja");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La queja ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@Secured({"ROLE_CLIENT"})
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") int id) {
		Map<String, Object> response = new HashMap<>();

		Claim claim = claimService.findById(id);

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

			String nombreFotoAnterior = claim.getAttachment();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			claim.setAttachment(nombreArchivo);

			claimService.saveClaim(claim);

			response.put("claim", claim);
			response.put("mensaje", "Has subido correctamente el archivo: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_CLIENT","ROLE_REPORTER"})
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
