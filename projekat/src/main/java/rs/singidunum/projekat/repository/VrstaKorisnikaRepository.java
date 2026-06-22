package rs.singidunum.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.singidunum.projekat.model.VrstaKorisnika;

@Repository
public interface VrstaKorisnikaRepository extends JpaRepository<VrstaKorisnika, Long>{

}
