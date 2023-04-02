package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Film;
import rva.model.Rezervacija;
import rva.model.Sala;
import rva.repository.RezervacijaRepository;

@Service
public class RezervacijaService {

	@Autowired
	private RezervacijaRepository repo;

	public List<Rezervacija> getAll() {
		return repo.findAll();
	}

	public Optional<Rezervacija> findById(long id) {
		return repo.findById(id);
	}

	public Optional<List<Rezervacija>> findByBrojOsoba(int brojOsoba) {
		Optional<List<Rezervacija>> lista = Optional.of(repo.findByBrojOsobaGreaterThanOrderById(brojOsoba));
		return lista;
	}

	public Optional<List<Rezervacija>> findRezervacijaByFilm(Film film) {
		Optional<List<Rezervacija>> lista = Optional.of(repo.findByFilm(film));
		return lista;
	}

	public Optional<List<Rezervacija>> findRezervacijaBySala(Sala sala) {
		Optional<List<Rezervacija>> lista = Optional.of(repo.findBySala(sala));
		return lista;
	}

	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		return repo.save(rezervacija);
	}

	public boolean existsById(long id) {
		if (findById(id).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}
}