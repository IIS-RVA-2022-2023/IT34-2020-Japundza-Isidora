package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bioskop;
import rva.repository.BioskopRepository;

@Service
public class BioskopService {

	@Autowired
	private BioskopRepository repo;
	
	public List<Bioskop> getAll() { 
		return repo.findAll();
	}

	public Optional<Bioskop> getById(long id) { 
		return repo.findById(id);
	}
	
	public Optional<List<Bioskop>> getByNaziv(String naziv) {
		Optional<List<Bioskop>> bioskopi = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return bioskopi;
	}
	
	public Bioskop addBioskop(Bioskop bioskop) { 
		return repo.save(bioskop);
	}
	
	public boolean existsById(long id) {
		return getById(id).isPresent();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
}
