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

import rva.model.Film;
import rva.service.FilmService;

@RestController
@CrossOrigin
public class FilmController {

	@Autowired
	private FilmService service;

	@GetMapping("/film")
	public List<Film> getAllFilm() {
		return service.getAll();
	}

	@GetMapping("/film/{id}")
	public ResponseEntity<?> getFilmById(@PathVariable long id) {
		if (service.existsById(id)) {
			return ResponseEntity.ok(service.getById(id).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested resource does not exist.");
		}
	}

	@GetMapping("/film/naziv/{naziv}")
	public ResponseEntity<?> getFilmByNaziv(@PathVariable String naziv) {
		List<Film> filmovi = service.getByNaziv(naziv).get();
		if (!filmovi.isEmpty()) {
			return ResponseEntity.ok(filmovi);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resources with requested naziv: " + naziv + " are not found.");
		}
	}

	@PostMapping("/film")
	public ResponseEntity<?> createFilm(@RequestBody Film film) {
		Film savedFilm;

		if (!service.existsById(film.getId())) {
			savedFilm = service.addFilm(film);
		} else {
			List<Film> lista = service.getAll();
			long najvecaVrednost = 1;
			for (int i = 0; i < lista.size(); i++) {
				if (najvecaVrednost <= lista.get(i).getId()) {
					najvecaVrednost = lista.get(i).getId();
				}
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
			}
			film.setId(najvecaVrednost);
			savedFilm = service.addFilm(film);
		}

		URI uri = URI.create("/film/" + savedFilm.getId());
		return ResponseEntity.created(uri).body(savedFilm);
	}

	@PutMapping("/film/{id}")
	public ResponseEntity<?> updateFilm(@RequestBody Film film, @PathVariable long id) {
		if (service.existsById(id)) {
			Film savedFilm = service.addFilm(film);
			return ResponseEntity.ok(savedFilm);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been found.");
		}
	}

	@DeleteMapping("/film/{id}")
	public ResponseEntity<String> deleteFilm(@PathVariable long id) {
		if (service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.ok("Resource with requested ID: " + id + " has been deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested ID: " + id + " has not been deleted.");
		}
	}
}