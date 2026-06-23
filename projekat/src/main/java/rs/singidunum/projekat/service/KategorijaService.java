package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Kategorija;
import rs.singidunum.projekat.repository.KategorijaRepository;

@Service
public class KategorijaService {
	
	private final KategorijaRepository kategorijaRepository;
	
	public KategorijaService(KategorijaRepository kategorijaRepository) {
		this.kategorijaRepository = kategorijaRepository;
	}
	
	public List<Kategorija> findAll() {
		return kategorijaRepository.findAll();
	}
	
	public Kategorija findById(Long id) {
		return kategorijaRepository.findById(id).orElseThrow(()-> new RuntimeException("Nepostojeca kategorija"));
	}
	
	public List<Kategorija> pretraga(String naziv) {
		return kategorijaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	// validacija kategorije
	private void validiraj(Kategorija kategorija) {

	    if(kategorija.getNaziv() == null ||
	       kategorija.getNaziv().trim().isEmpty()) {
	        throw new RuntimeException("Naziv kategorije je obavezan");
	    }
	}
	
	public Kategorija save(Kategorija kategorija) {
		validiraj(kategorija);
		return kategorijaRepository.save(kategorija);
	}
	
	public Kategorija update(Long id, Kategorija izmeniKategoriju) {
		Kategorija postojecaKategorija = findById(id);
		validiraj(izmeniKategoriju);
		postojecaKategorija.setNaziv(izmeniKategoriju.getNaziv());
		return kategorijaRepository.save(postojecaKategorija);
	}
	
	public void delete(Long id) {
		Kategorija kategorija = findById(id);
		kategorijaRepository.delete(kategorija);
	}
}
