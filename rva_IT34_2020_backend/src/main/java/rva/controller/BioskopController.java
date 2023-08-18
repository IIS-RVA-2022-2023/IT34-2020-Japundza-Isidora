package rva.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin; //?
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Bioskop;
import rva.service.BioskopService;

@RestController
@CrossOrigin 
public class BioskopController {

	@Autowired
	private BioskopService service;

	@GetMapping("/bioskop")
	public List<Bioskop> getAllBioskop() {
		return service.getAll();
	}

	@GetMapping("/bioskop/{id}")
	public ResponseEntity<?> getBioskopById(@PathVariable long id) {
		if (service.existsById(id)) {
			return ResponseEntity.ok(service.getById(id).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested resource does not exist.");
		}
	}

	@GetMapping("/bioskop/naziv/{naziv}")
	public ResponseEntity<?> getBioskopByNaziv(@PathVariable String naziv) {
		List<Bioskop> bioskopi = service.getByNaziv(naziv).get();
		if (!bioskopi.isEmpty()) {
			return ResponseEntity.ok(bioskopi);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested naziv: " + naziv + " are not found.");
		}
	}

	@PostMapping("/bioskop")
	public ResponseEntity<?> createBioskop(@RequestBody Bioskop bioskop) {
		Bioskop savedBioskop;

		if (!service.existsById(bioskop.getId())) {
			savedBioskop = service.addBioskop(bioskop);
		} else {
			List<Bioskop> lista = service.getAll();
			long najvecaVrednost = 1;
			for (int i = 0; i < lista.size(); i++) {
				if (najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			bioskop.setId(najvecaVrednost);
			savedBioskop = service.addBioskop(bioskop);
		}

		URI uri = URI.create("/bioskop/" + savedBioskop.getId());
		return ResponseEntity.created(uri).body(savedBioskop);
	}

	@PutMapping("/bioskop/{id}")
	public ResponseEntity<?> updateBioskop(@RequestBody Bioskop bioskop, @PathVariable long id) {
		if (service.existsById(id)) {
			Bioskop savedBioskop = service.addBioskop(bioskop);
			return ResponseEntity.ok(savedBioskop);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been found.");

		}
	}

	@DeleteMapping("/bioskop/{id}")
	public ResponseEntity<String> deleteBioskop(@PathVariable long id) {
		if (service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with requested ID: " + id + " has been deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been deleted.");
		}
	}
}