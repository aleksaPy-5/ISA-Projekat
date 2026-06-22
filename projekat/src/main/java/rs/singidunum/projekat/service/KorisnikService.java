package rs.singidunum.projekat.service;

import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Korisnik;
import rs.singidunum.projekat.repository.KategorijaRepository;
import rs.singidunum.projekat.repository.KorisnikRepository;

@Service
public class KorisnikService {

  
	
	private final KorisnikRepository korisnikRepository;
	
	public KorisnikService(KorisnikRepository korisnikRepository, KategorijaRepository kategorijaRepository) {
		this.korisnikRepository = korisnikRepository;
		
	}
	
	//get all
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
	
	// get by id
	public Korisnik findById(Long id) {
		return korisnikRepository.findById(id).orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));
	}
	
	// sacuvaj korisnika
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}
	
	//update korisnika
	public Korisnik update(Long id, Korisnik izmeniKorisnika) {
		Korisnik postojeciKorisnik = findById(id);
		postojeciKorisnik.setIme(izmeniKorisnika.getIme());
		return korisnikRepository.save(postojeciKorisnik);
	}
	
	//brisanje korisnika
	public void delete(Long id) {
		Korisnik korisnik = findById(id);
		korisnikRepository.delete(korisnik);
	}
	
}
