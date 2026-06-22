package rs.singidunum.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.singidunum.projekat.model.Kategorija;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, Long>{

}
