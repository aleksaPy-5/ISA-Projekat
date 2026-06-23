package rs.singidunum.projekat.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Porudzbina;
import rs.singidunum.projekat.repository.PorudzbinaRepository;
import rs.singidunum.projekat.repository.ProizvodRepository;


@Service
public class PorudzbinaService {


    private final PorudzbinaRepository porudzbinaRepository;
    private final ProizvodRepository proizvodRepository;
	
	public PorudzbinaService(PorudzbinaRepository porudzbinaRepository,ProizvodRepository proizvodRepository) {
		this.porudzbinaRepository = porudzbinaRepository;
		this.proizvodRepository = proizvodRepository;

	}
	
	private void validiraj(Porudzbina porudzbina) {
		
		if(porudzbina.getKorisnik() == null) {
			throw new RuntimeException("Korisnik je obavezan");
		}
		
		if(porudzbina.getStavke() == null || porudzbina.getStavke().isEmpty()) {
			throw new RuntimeException("Porudzbina mora imati stavke");
		}
		
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
	
	public Porudzbina napraviPorudzbinu(Porudzbina porudzbina) {
		validiraj(porudzbina);
		
		BigDecimal ukupnaCena = BigDecimal.ZERO;
		
		for(var stavka : porudzbina.getStavke()) {
			var proizvod = stavka.getProizvod();
			if(proizvod.getKolicinaNaLageru() < stavka.getKolicina()) {
				throw new RuntimeException ("Nema dovoljno: " + proizvod.getNaziv());
			}
			
			stavka.setPorudzbina(porudzbina);
			
			stavka.setCena(proizvod.getCena());
			// skidanje sa lagera
			proizvod.setKolicinaNaLageru(
					proizvod.getKolicinaNaLageru() - stavka.getKolicina());
			proizvodRepository.save(proizvod);
			ukupnaCena = ukupnaCena.add(
				    stavka.getCena().multiply(BigDecimal.valueOf(stavka.getKolicina()))
				);
		}
		porudzbina.setUkupnaCena(ukupnaCena);
		porudzbina.setStatus("Na cekanju");
		return porudzbinaRepository.save(porudzbina);
	}
	
	public Porudzbina update(Long id, Porudzbina izmeniPorudzbinu) {
		Porudzbina postojecaPorudzbina = findById(id);
		if(izmeniPorudzbinu.getStatus() == null) {
			throw new RuntimeException("Status je obavezan");
		}
		
		postojecaPorudzbina.setStatus(izmeniPorudzbinu.getStatus());
	    postojecaPorudzbina.setUkupnaCena(izmeniPorudzbinu.getUkupnaCena());

	    return porudzbinaRepository.save(postojecaPorudzbina);
	}
	
	public void delete(Long id) {
		Porudzbina porudzbina = findById(id);
		porudzbinaRepository.delete(porudzbina);
		
	}
}
