package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Proizvod;
import rs.singidunum.projekat.repository.ProizvodRepository;

@Service
public class ProizvodService {
	
	private final ProizvodRepository proizvodRepository;
	
	public ProizvodService(ProizvodRepository proizvodRepository ) {
		this.proizvodRepository = proizvodRepository;
	}
	
	//get all
	public List<Proizvod> findAll() {
		return proizvodRepository.findAll();
	}
	
	// get by id
	public Proizvod findById(Long id) {
		return proizvodRepository.findById(id).orElseThrow(()-> new RuntimeException("Proizvod ne postoji"));
	}
	
	//sacuvaj proizvod
	public Proizvod save(Proizvod proizvod) {
		return proizvodRepository.save(proizvod);
	}
	
	//update proizvoda
	public Proizvod update(Long id, Proizvod izmeniProizvod) {
		Proizvod postojeciProizvod = findById(id);
		postojeciProizvod.setNaziv(izmeniProizvod.getNaziv());
		postojeciProizvod.setCena(izmeniProizvod.getCena());
		postojeciProizvod.setKolicinaNaLageru(izmeniProizvod.getKolicinaNaLageru());
		return proizvodRepository.save(postojeciProizvod);
	}
	
	//brisanje proizvoda
	public void delete(Long id) {
		Proizvod proizvod = findById(id);
		proizvodRepository.delete(proizvod);
	}
}
