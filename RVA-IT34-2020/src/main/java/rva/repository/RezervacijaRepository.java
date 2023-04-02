package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Film;
import rva.model.Rezervacija;
import rva.model.Sala;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
	List<Rezervacija> findByBrojOsobaGreaterThanOrderById(int brojOsoba);

	List<Rezervacija> findByFilm(Film film);

	List<Rezervacija> findBySala(Sala sala);
}
