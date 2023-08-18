package rva.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Sala;
import rva.service.BioskopService;
import rva.service.SalaService;

@RestController
@CrossOrigin
public class SalaController {

	@Autowired
	private SalaService service;

	@Autowired
	private BioskopService bioskopService;

	@GetMapping("/sala")
	public List<Sala> getAllSala() {
		return service.getAll();
	}

	@GetMapping("/sala/{id}")
	public ResponseEntity<?> findSalaById(@PathVariable long id) {
		if (service.findById(id).isPresent()) {
			Sala sala = service.findById(id).get();
			return ResponseEntity.ok(sala);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Requested resource with ID: " + id + "has not been found.");
		}
	}

	@GetMapping("/sala/kapacitet/{kapacitet}")
	public ResponseEntity<?> findSalaByKapacitetGreaterThan(@PathVariable int kapacitet) {
		if (service.findByKapacitet(kapacitet).get().isEmpty()) {
			return ResponseEntity.ok(service.findByKapacitet(kapacitet).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested kapacitet: " + kapacitet + " has not been found.");
		}
	}

	@GetMapping("/sala/bioskop/{id}")
	public ResponseEntity<?> getSalaByBioskop(@PathVariable long id) {
		if (!service.findSalaByBioskop(bioskopService.getById(id).get()).get().isEmpty()) {
			return ResponseEntity.ok(service.findSalaByBioskop(bioskopService.getById(id).get()).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested value of foreign key bioskop: " + id + " have not been found.");
		}
	}

	@PostMapping("/sala")
	public ResponseEntity<Sala> createSala(@RequestBody Sala sala) {
		Sala savedSala;

		if (!service.existsById(sala.getId())) {
			savedSala = service.addSala(sala);
		} else {
			List<Sala> lista = service.getAll();
			long najvecaVrednost = 1;
			for (int i = 0; i < lista.size(); i++) {
				if (najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			sala.setId(najvecaVrednost);
			savedSala = service.addSala(sala);
		}

		URI uri = URI.create("/sala/" + savedSala.getId());
		return ResponseEntity.created(uri).body(savedSala);
	}

	@PutMapping("/sala/{id}")
	public ResponseEntity<?> updateSala(@RequestBody Sala sala, @PathVariable long id) {
		if (service.existsById(id)) {
			return ResponseEntity.ok(service.addSala(sala));
			// Sala savedSala = service.addSala(sala);
			// return ResponseEntity.ok(savedSala);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been found.");
		}
	}

	@DeleteMapping("/sala/{id}")
	public ResponseEntity<String> deleteSala(@PathVariable long id) {
		if (service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with requested ID: " + id + " has been deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been deleted.");
		}
	}
}