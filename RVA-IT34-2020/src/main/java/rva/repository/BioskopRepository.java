package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Bioskop;

public interface BioskopRepository extends JpaRepository<Bioskop, Long> {

	List<Bioskop> findByNazivContainingIgnoreCase(String naziv); 
}
