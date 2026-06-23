package rs.singidunum.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.singidunum.projekat.model.Kategorija;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, Long>{
	List<Kategorija> findByNazivContainingIgnoreCase(String naziv);
	boolean existsByNazivIgnoreCase(String naziv);
}
