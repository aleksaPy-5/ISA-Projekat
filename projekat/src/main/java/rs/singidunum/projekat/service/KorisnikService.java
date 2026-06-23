package rs.singidunum.projekat.service;

import java.util.List;


import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Korisnik;

import rs.singidunum.projekat.repository.KorisnikRepository;

@Service
public class KorisnikService {

  
	
	private final KorisnikRepository korisnikRepository;
	
	public KorisnikService(KorisnikRepository korisnikRepository) {
		this.korisnikRepository = korisnikRepository;
		
	}
	
	//get all
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
	
	// validacija korisnika
	private void validiraj(Korisnik korisnik) {
		if(korisnik.getIme() == null || korisnik.getIme().trim().isEmpty()) {
			throw new RuntimeException("Ime je obavezno");
		}
		
		if(korisnik.getEmail() == null || korisnik.getEmail().trim().isEmpty()) {
			throw new RuntimeException("Email je obavezan");
		}
		
		if(korisnik.getLozinka() == null || korisnik.getLozinka().length() < 6) {
			throw new RuntimeException ("Lozinka mora imati najmanje 6 karaktera");
		}
	}
	
	
	// get by id
	public Korisnik findById(Long id) {
		return korisnikRepository.findById(id).orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));
	}
	
	// sacuvaj korisnika
	public Korisnik save(Korisnik korisnik) {
		
		validiraj(korisnik);
		if(korisnikRepository.existsByEmail(korisnik.getEmail())) {
			throw new RuntimeException("Email vec postoji");
		}
		return korisnikRepository.save(korisnik);
	}
	
	public Korisnik login(String email, String lozinka) {
		Korisnik korisnik = korisnikRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));
		if(!korisnik.getLozinka().equals(lozinka)) {
			throw new RuntimeException("Pogresna lozinka");
		}
		return korisnik;
	}
	
	//update korisnika
	public Korisnik update(Long id, Korisnik izmeniKorisnika) {
		validiraj(izmeniKorisnika);
		Korisnik postojeciKorisnik = findById(id);
		postojeciKorisnik.setIme(izmeniKorisnika.getIme());
		postojeciKorisnik.setPrezime(izmeniKorisnika.getPrezime());
	    postojeciKorisnik.setEmail(izmeniKorisnika.getEmail());
	    postojeciKorisnik.setLozinka(izmeniKorisnika.getLozinka());
	    postojeciKorisnik.setTelefon(izmeniKorisnika.getTelefon());
	    postojeciKorisnik.setAdresa(izmeniKorisnika.getAdresa());
		return korisnikRepository.save(postojeciKorisnik);
	}
	
	//brisanje korisnika
	public void delete(Long id) {
		Korisnik korisnik = findById(id);
		korisnikRepository.delete(korisnik);
	}
	
}
