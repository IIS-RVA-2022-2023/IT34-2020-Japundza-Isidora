package rva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {
	// prvi je entitu tip podatka
	// tip podatka primarnog kljuca

}
