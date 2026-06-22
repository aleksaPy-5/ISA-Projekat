package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.repository.StavkaPorudzbineRepository;

@Service
public class StavkaPorudzbineService {
	
	private final StavkaPorudzbineRepository stavkaPorudzbineRepository;
	
	public StavkaPorudzbineService(StavkaPorudzbineRepository stavkaPorudzbineRepository) {
		this.stavkaPorudzbineRepository = stavkaPorudzbineRepository;
	}
	
	public List <StavkaPorudzbine> findAll() {
		
	}

}
