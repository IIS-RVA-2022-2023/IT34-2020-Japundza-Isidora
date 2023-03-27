package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Bioskop;
import rva.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {
	// prvi je entitu tip podatka
	// tip podatka primarnog kljuca
	
	
	//minimalno jedna metoda pretrage po jednom obelezju - varchar ili numeric/integer
	//bar u jednom repositorijumu mora biti obelezje integer/numeruc
	List<Sala> findByKapacitetGreaterThanOrderById(int kapacitet);
	List<Sala> findByBioskop(Bioskop bioskop);
}
