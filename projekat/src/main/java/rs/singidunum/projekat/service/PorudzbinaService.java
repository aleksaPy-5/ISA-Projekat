package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Porudzbina;
import rs.singidunum.projekat.repository.PorudzbinaRepository;


@Service
public class PorudzbinaService {


    private final PorudzbinaRepository porudzbinaRepository;
	
	public PorudzbinaService(PorudzbinaRepository porudzbinaRepository) {
		this.porudzbinaRepository = porudzbinaRepository;

	}
	
	public List <Porudzbina> findAll() {
		return porudzbinaRepository.findAll();
	}
	
	public Porudzbina findById(Long id) {
		return porudzbinaRepository.findById(id).orElseThrow(()-> new RuntimeException("Poruzbina ne postoji"));
	}
	
	public Porudzbina save(Porudzbina porudzbina) {
		return porudzbinaRepository.save(porudzbina);
	}
}
