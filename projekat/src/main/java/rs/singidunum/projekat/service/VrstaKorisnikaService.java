package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.VrstaKorisnika;
import rs.singidunum.projekat.repository.VrstaKorisnikaRepository;

@Service
public class VrstaKorisnikaService {

    
	private final VrstaKorisnikaRepository vrstaKorisnikaRepository;
	
	public VrstaKorisnikaService(VrstaKorisnikaRepository vrstaKorisnikaRepository ) {
		this.vrstaKorisnikaRepository = vrstaKorisnikaRepository;
	}
	
	private void validiraj(VrstaKorisnika vrstaKorisnika) {
		if(vrstaKorisnika.getNaziv() == null || vrstaKorisnika.getNaziv().trim().isEmpty()) {
			throw new RuntimeException("Naziv tipa korisnika je obavezan");
		}
	}
	
	//get all
	public List<VrstaKorisnika> findAll() {
		return vrstaKorisnikaRepository.findAll();
	}
	
	//get by id
	public VrstaKorisnika findById(Long id) {
		return vrstaKorisnikaRepository.findById(id).orElseThrow(() -> new RuntimeException("Vrsta korisnika ne postoji"));
	}
	
	// sacuvaj vrstu korisnika
	public VrstaKorisnika save (VrstaKorisnika vrstaKorisnika) {
		validiraj(vrstaKorisnika);
		return vrstaKorisnikaRepository.save(vrstaKorisnika);
	}
	
	//update vrsta korisnika
	public VrstaKorisnika update(Long id, VrstaKorisnika izmenjenKorisnik) {
		VrstaKorisnika postojeciKorisnik = findById(id);	
		validiraj(izmenjenKorisnik);
		postojeciKorisnik.setNaziv(izmenjenKorisnik.getNaziv());		
		return vrstaKorisnikaRepository.save(postojeciKorisnik);
	}
	
	//brisanje vrste korisnika
	public void delete(Long id) {
		VrstaKorisnika vrstaKorisnika = findById(id);
		vrstaKorisnikaRepository.delete(vrstaKorisnika);
	}
}
