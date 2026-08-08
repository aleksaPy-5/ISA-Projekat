package rs.singidunum.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.singidunum.projekat.model.StavkaPorudzbine;

@Repository
public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Long>{
	List<StavkaPorudzbine> findByPorudzbinaId(Long porudzbinaId);
}
