package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bioskop;
import rva.model.Sala;
import rva.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository repo;

	public List<Sala> getAll() {
		return repo.findAll();
	}

	public Optional<Sala> findById(long id) {
		return repo.findById(id);
	}

	public Optional<List<Sala>> findByKapacitet(int kapacitet) {
		Optional<List<Sala>> lista = Optional.of(repo.findByKapacitetGreaterThanOrderById(kapacitet));
		return lista;
	}

	public Optional<List<Sala>> findSalaByBioskop(Bioskop bioskop) {
		Optional<List<Sala>> lista = Optional.of(repo.findByBioskop(bioskop));
		return lista;
	}

	public Sala addSala(Sala sala) {
		return repo.save(sala);
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