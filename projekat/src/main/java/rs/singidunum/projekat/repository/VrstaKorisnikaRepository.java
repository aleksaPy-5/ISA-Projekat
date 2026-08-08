package rs.singidunum.projekat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.singidunum.projekat.model.VrstaKorisnika;

@Repository
public interface VrstaKorisnikaRepository extends JpaRepository<VrstaKorisnika, Long>{
	Optional<VrstaKorisnika> findByNaziv(String naziv);
}
