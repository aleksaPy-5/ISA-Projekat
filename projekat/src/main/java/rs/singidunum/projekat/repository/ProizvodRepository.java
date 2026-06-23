package rs.singidunum.projekat.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.singidunum.projekat.model.Proizvod;


@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Long>{
	
	List<Proizvod> findByNazivContainingIgnoreCase(String naziv);
	List<Proizvod> findByCenaBetween(BigDecimal min, BigDecimal max);
}




