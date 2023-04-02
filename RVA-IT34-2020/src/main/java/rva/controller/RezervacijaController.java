package rva.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Rezervacija;
import rva.model.Sala;
import rva.service.FilmService;
import rva.service.RezervacijaService;
import rva.service.SalaService;

@RestController
public class RezervacijaController {

	@Autowired
	private RezervacijaService service;

	@Autowired
	private FilmService filmService;

	@Autowired
	private SalaService salaService;

	@GetMapping("/rezervacija")
	public List<Rezervacija> getAllRezervacija() {
		return service.getAll();
	}

	@GetMapping("/rezervacija/{id}")
	public ResponseEntity<?> findRezervacijaById(@PathVariable long id) {
		if (service.findById(id).isPresent()) {
			Rezervacija rezervacija = service.findById(id).get();
			return ResponseEntity.ok(rezervacija);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Requested resource with ID: " + id + "has not been found.");
		}
	}

	@GetMapping("/rezervacija/brojOsoba/{brojOsoba}")
	public ResponseEntity<?> findRezervacijaByBrojOsobaGreaterThan(@PathVariable int brojOsoba) {
		if (service.findByBrojOsoba(brojOsoba).get().isEmpty()) {
			return ResponseEntity.ok(service.findByBrojOsoba(brojOsoba).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested brojOsoba: " + brojOsoba + " has not been found.");
		}
	}

	@GetMapping("/rezervacija/film/{id}")
	public ResponseEntity<?> getRezervacijaByFilm(@PathVariable long id) {
		if (!service.findRezervacijaByFilm(filmService.getById(id).get()).get().isEmpty()) {
			return ResponseEntity.ok(service.findRezervacijaByFilm(filmService.getById(id).get()).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested value of foreign key film: " + id + " have not been found.");
		}
	}

	@GetMapping("/rezervacija/sala/{id}")
	public ResponseEntity<?> getRezervacijaBySala(@PathVariable long id) {
		if (!service.findRezervacijaBySala(salaService.findById(id).get()).get().isEmpty()) {
			return ResponseEntity.ok(service.findRezervacijaBySala(salaService.findById(id).get()).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested value of foreign key sala: " + id + " have not been found.");
		}
	}

	@PostMapping("/rezervacija")
	public ResponseEntity<Rezervacija> createRezervacija(@RequestBody Rezervacija rezervacija) {
		Rezervacija savedRezervacija;

		if (!service.existsById(rezervacija.getId())) {
			savedRezervacija = service.addRezervacija(rezervacija);
		} else {
			List<Rezervacija> lista = service.getAll();
			long najvecaVrednost = 1;
			for (int i = 0; i < lista.size(); i++) {
				if (najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			rezervacija.setId(najvecaVrednost);
			savedRezervacija = service.addRezervacija(rezervacija);
		}

		URI uri = URI.create("/rezervacija/" + savedRezervacija.getId());
		return ResponseEntity.created(uri).body(savedRezervacija);
	}

	@PutMapping("/rezervacija/{id}")
	public ResponseEntity<?> updateRezervacija(@RequestBody Rezervacija rezervacija, @PathVariable long id) {
		if (service.existsById(id)) {
			return ResponseEntity.ok(service.addRezervacija(rezervacija));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: \" + id + \" has not been found.");
		}
	}

	@DeleteMapping("/rezervacija/{id}")
	public ResponseEntity<String> deleteRezervacija(@PathVariable long id) {
		if (service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with requested ID: " + id + " has been deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been deleted.");
		}
	}
}