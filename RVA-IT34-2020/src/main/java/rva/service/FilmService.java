package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Film;
import rva.repository.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository repo;

	public List<Film> getAll() {
		return repo.findAll();
	}

	public Optional<Film> getById(long id) {
		return repo.findById(id);
	}

	public Optional<List<Film>> getByNaziv(String naziv) {
		Optional<List<Film>> filmovi = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return filmovi;
	}

	public Film addFilm(Film film) {
		return repo.save(film);
	}

	public boolean existsById(long id) {
		return getById(id).isPresent();
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}
}
