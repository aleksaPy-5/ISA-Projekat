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
	
	public Kategorija save(Kategorija kategorija) {
		return kategorijaRepository.save(kategorija);
	}
	
	public Kategorija update(Long id, Kategorija izmeniKategoriju) {
		Kategorija postojecaKategorija = findById(id);
		postojecaKategorija.setNaziv(izmeniKategoriju.getNaziv());
		return kategorijaRepository.save(postojecaKategorija);
	}
	
	public void delete(Long id) {
		Kategorija kategorija = findById(id);
		kategorijaRepository.delete(kategorija);
	}
}
