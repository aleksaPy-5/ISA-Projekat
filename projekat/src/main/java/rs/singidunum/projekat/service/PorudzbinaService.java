package rs.singidunum.projekat.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rs.singidunum.projekat.model.Korisnik;
import rs.singidunum.projekat.model.Porudzbina;
import rs.singidunum.projekat.model.StavkaPorudzbine;
import rs.singidunum.projekat.repository.PorudzbinaRepository;
import rs.singidunum.projekat.repository.ProizvodRepository;


@Service
public class PorudzbinaService {


	private final PorudzbinaRepository porudzbinaRepository;
	private final ProizvodRepository proizvodRepository;



	public PorudzbinaService(PorudzbinaRepository porudzbinaRepository,
			ProizvodRepository proizvodRepository) {

		this.porudzbinaRepository = porudzbinaRepository;
		this.proizvodRepository = proizvodRepository;

	}




	// validacija porudzbine
	private void validiraj(Porudzbina porudzbina) {


		if(porudzbina.getKorisnik() == null) {

			throw new RuntimeException(
					"Korisnik je obavezan");

		}



		if(porudzbina.getStavke() == null ||
				porudzbina.getStavke().isEmpty()) {


			throw new RuntimeException(
					"Porudzbina mora imati stavke");

		}

	}






	// prikaz svih porudzbina
	public List<Porudzbina> findAll() {

		return porudzbinaRepository.findAll();

	}





	// porudzbine jednog korisnika
	public List<Porudzbina> findByKorisnikId(Long korisnikId) {

		return porudzbinaRepository.findByKorisnikId(korisnikId);

	}






	// pronalazak po id-u
	public Porudzbina findById(Long id) {


		return porudzbinaRepository.findById(id)
				.orElseThrow(() ->
				new RuntimeException(
						"Porudzbina ne postoji"));

	}







	// kreiranje porudzbine
	@Transactional
	public Porudzbina napraviPorudzbinu(
			Porudzbina porudzbina,
			Korisnik korisnik) {



		porudzbina.setKorisnik(korisnik);



		validiraj(porudzbina);



		BigDecimal ukupnaCena = BigDecimal.ZERO;



		for(StavkaPorudzbine stavka :
			porudzbina.getStavke()) {



			var proizvod = stavka.getProizvod();



			if(stavka.getKolicina() <= 0) {

				throw new RuntimeException(
						"Kolicina mora biti veca od 0");

			}




			if(proizvod.getKolicinaNaLageru()
					< stavka.getKolicina()) {


				throw new RuntimeException(
						"Nema dovoljno proizvoda: "
						+ proizvod.getNaziv());

			}




			stavka.setPorudzbina(porudzbina);



			stavka.setCena(
					proizvod.getCena());




			proizvod.setKolicinaNaLageru(
					proizvod.getKolicinaNaLageru()
					- stavka.getKolicina());



			proizvodRepository.save(proizvod);




			ukupnaCena = ukupnaCena.add(
					stavka.getCena()
					.multiply(
					BigDecimal.valueOf(
					stavka.getKolicina())));

		}






		// popust 10% preko 10000 din
		if(ukupnaCena.compareTo(
				new BigDecimal("10000")) > 0) {


			ukupnaCena =
					ukupnaCena.multiply(
					new BigDecimal("0.90"));

		}




		porudzbina.setUkupnaCena(
				ukupnaCena);



		porudzbina.setStatus(
				"Na cekanju");




		return porudzbinaRepository.save(
				porudzbina);

	}








	// kupovina jednog proizvoda
	@Transactional
	public Porudzbina kupi(Long proizvodId,
			Long korisnikId,
			int kolicina) {



		if(kolicina <= 0) {

			throw new RuntimeException(
					"Kolicina mora biti veca od 0");

		}




		var proizvod =
				proizvodRepository.findById(proizvodId)
				.orElseThrow(() ->
				new RuntimeException(
						"Proizvod ne postoji"));





		if(proizvod.getKolicinaNaLageru()
				< kolicina) {


			throw new RuntimeException(
					"Nema dovoljno proizvoda na lageru");

		}




		Korisnik korisnik = new Korisnik();

		korisnik.setId(korisnikId);





		Porudzbina porudzbina =
				new Porudzbina();



		porudzbina.setKorisnik(korisnik);





		StavkaPorudzbine stavka =
				new StavkaPorudzbine();



		stavka.setProizvod(proizvod);

		stavka.setKolicina(kolicina);

		stavka.setCena(proizvod.getCena());

		stavka.setPorudzbina(porudzbina);




		porudzbina.getStavke()
				.add(stavka);




		return napraviPorudzbinu(
				porudzbina,
				korisnik);

	}








	// provera statusa
	private void proveriStatus(String status) {


		if(status == null ||
				status.isBlank()) {


			throw new RuntimeException(
					"Status je obavezan");

		}




		if(!status.equals("Na cekanju") &&
				!status.equals("Poslata") &&
				!status.equals("Zavrsena") &&
				!status.equals("Otkazana")) {


			throw new RuntimeException(
					"Nepostojeci status");

		}

	}








	// otkazivanje porudzbine
	@Transactional
	public Porudzbina otkaziPorudzbinu(Long id) {


		Porudzbina porudzbina =
				findById(id);




		if(porudzbina.getStatus()
				.equals("Otkazana")) {


			throw new RuntimeException(
					"Porudzbina je vec otkazana");

		}





		if(porudzbina.getStatus()
				.equals("Zavrsena")) {


			throw new RuntimeException(
					"Zavrsena porudzbina se ne moze otkazati");

		}





		for(StavkaPorudzbine stavka :
			porudzbina.getStavke()) {



			var proizvod =
					stavka.getProizvod();



			proizvod.setKolicinaNaLageru(
					proizvod.getKolicinaNaLageru()
					+ stavka.getKolicina());



			proizvodRepository.save(proizvod);

		}




		porudzbina.setStatus(
				"Otkazana");




		return porudzbinaRepository.save(
				porudzbina);

	}








	// promena statusa
	public Porudzbina update(Long id,
			Porudzbina izmeniPorudzbinu) {


		Porudzbina postojeca =
				findById(id);



		proveriStatus(
				izmeniPorudzbinu.getStatus());



		postojeca.setStatus(
				izmeniPorudzbinu.getStatus());



		return porudzbinaRepository.save(
				postojeca);

	}







	// brisanje
	public void delete(Long id) {


		Porudzbina porudzbina =
				findById(id);



		porudzbinaRepository.delete(
				porudzbina);

	}


}