package rs.singidunum.projekat.service;

import java.math.BigDecimal;
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
	
	// filtriranje po ceni proizvoda
	public List<Proizvod> filtrirajPoCeni(BigDecimal min, BigDecimal max) {
	    return proizvodRepository.findAll()
	            .stream()
	            .filter(p ->
	                p.getCena().compareTo(min) >= 0 &&
	                p.getCena().compareTo(max) <= 0)
	            .toList();
	}
	
	// dopuna lagera
	public Proizvod dopuniLager(Long id, int kolicina) {
	    Proizvod proizvod = findById(id);

	    if(kolicina <= 0) {
	        throw new RuntimeException("Kolicina mora biti veca od 0");
	    }

	    proizvod.setKolicinaNaLageru(
	        proizvod.getKolicinaNaLageru() + kolicina
	    );

	    return proizvodRepository.save(proizvod);
	}
	
	// get by id
	public Proizvod findById(Long id) {
		return proizvodRepository.findById(id).orElseThrow(()-> new RuntimeException("Proizvod ne postoji"));
	}
	
	// pretraga proizvoda po nazivu
	public List<Proizvod> pretraga(String naziv) {
		return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	// provera ispravnosti cene i kolicine
	private void validiraj(Proizvod proizvod) {
	    if(proizvod.getCena().compareTo(BigDecimal.ZERO) <= 0) {
	        throw new RuntimeException("Cena mora biti veca od 0");
	    }

	    if(proizvod.getKolicinaNaLageru() < 0) {
	        throw new RuntimeException("Kolicina ne moze biti negativna");
	    }
	}
	
	//sacuvaj proizvod
	public Proizvod save(Proizvod proizvod) {	
		validiraj(proizvod);
		return proizvodRepository.save(proizvod);
	}
	
	public Proizvod kupiProizvod(Long id, int kolicina) {
	    Proizvod p = proizvodRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));
	    
	    if(p.getKolicinaNaLageru() == 0) {
	    	throw new RuntimeException("Proizvod nije dostupan!");
	    }
	    
	    if(kolicina <= 0) {
	        throw new RuntimeException("Količina mora biti veća od 0");
	    }

	    if(p.getKolicinaNaLageru() < kolicina) {
	        throw new RuntimeException("Nema dovoljno proizvoda na lageru");
	    }

	    p.setKolicinaNaLageru(
	            p.getKolicinaNaLageru() - kolicina
	    );

	    return proizvodRepository.save(p);
	}
	
	//update proizvoda
	public Proizvod update(Long id, Proizvod izmeniProizvod) {
		Proizvod postojeciProizvod = findById(id);
		postojeciProizvod.setNaziv(izmeniProizvod.getNaziv());
		postojeciProizvod.setCena(izmeniProizvod.getCena());
		postojeciProizvod.setKolicinaNaLageru(izmeniProizvod.getKolicinaNaLageru());
		validiraj(izmeniProizvod);
		return proizvodRepository.save(postojeciProizvod);
	}
	
	//brisanje proizvoda
	public void delete(Long id) {
		Proizvod proizvod = findById(id);
		proizvodRepository.delete(proizvod);
	}
}
