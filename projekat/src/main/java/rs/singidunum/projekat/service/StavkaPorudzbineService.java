package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.StavkaPorudzbine;
import rs.singidunum.projekat.repository.StavkaPorudzbineRepository;

@Service
public class StavkaPorudzbineService {
	
	private final StavkaPorudzbineRepository stavkaPorudzbineRepository;
	
	public StavkaPorudzbineService(StavkaPorudzbineRepository stavkaPorudzbineRepository) {
		this.stavkaPorudzbineRepository = stavkaPorudzbineRepository;
	}
	
	public List <StavkaPorudzbine> findAll() {
		return stavkaPorudzbineRepository.findAll();
	}
	
	public StavkaPorudzbine findById(Long id) {
		return stavkaPorudzbineRepository.findById(id).orElseThrow(()-> new RuntimeException("Stavka porudzbine ne postoji"));
	}
	
	public StavkaPorudzbine save(StavkaPorudzbine stavkaPorudzbine) {
		return stavkaPorudzbineRepository.save(stavkaPorudzbine);
	}
	
	public StavkaPorudzbine update(Long id, StavkaPorudzbine izmeniStavkaPorudzbine) {
	    StavkaPorudzbine postojecaStavkaPorudzbine = findById(id);

	    postojecaStavkaPorudzbine.setKolicina(izmeniStavkaPorudzbine.getKolicina());
	    postojecaStavkaPorudzbine.setCena(izmeniStavkaPorudzbine.getCena());

	    return stavkaPorudzbineRepository.save(postojecaStavkaPorudzbine);
	}
	
	public void delete(Long id) {
		StavkaPorudzbine stavkaPorudzbine = findById(id);
		stavkaPorudzbineRepository.delete(stavkaPorudzbine);
	}

}
