package rs.singidunum.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.singidunum.projekat.model.Proizvod;

@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Long>{

}
