package rs.singidunum.projekat.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Proizvod;
import rs.singidunum.projekat.repository.ProizvodRepository;


@Service
public class ProizvodService {


	private final ProizvodRepository proizvodRepository;


	public ProizvodService(ProizvodRepository proizvodRepository) {
		this.proizvodRepository = proizvodRepository;
	}



	// prikaz svih proizvoda
	public List<Proizvod> findAll() {

		return proizvodRepository.findAll();

	}



	// pronalazak proizvoda po id-u
	public Proizvod findById(Long id) {

		return proizvodRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));

	}



	// pretraga proizvoda po nazivu
	public List<Proizvod> pretraga(String naziv) {

		return proizvodRepository.findByNazivContainingIgnoreCase(naziv);

	}



	// filtriranje po ceni
	public List<Proizvod> filtrirajPoCeni(BigDecimal min, BigDecimal max) {

		return proizvodRepository.findByCenaBetween(min, max);

	}



	// validacija proizvoda
	private void validiraj(Proizvod proizvod) {


		if(proizvod.getCena().compareTo(BigDecimal.ZERO) <= 0) {

			throw new RuntimeException(
					"Cena mora biti veca od 0"
			);

		}


		if(proizvod.getKolicinaNaLageru() < 0) {

			throw new RuntimeException(
					"Kolicina ne moze biti negativna"
			);

		}

	}




	// dodavanje proizvoda
	public Proizvod save(Proizvod proizvod) {

		validiraj(proizvod);

		return proizvodRepository.save(proizvod);

	}




	// izmena proizvoda
	public Proizvod update(Long id, Proizvod izmeniProizvod) {


		Proizvod postojeciProizvod = findById(id);


		postojeciProizvod.setNaziv(
				izmeniProizvod.getNaziv()
		);


		postojeciProizvod.setOpis(
				izmeniProizvod.getOpis()
		);


		postojeciProizvod.setCena(
				izmeniProizvod.getCena()
		);


		postojeciProizvod.setKolicinaNaLageru(
				izmeniProizvod.getKolicinaNaLageru()
		);


		postojeciProizvod.setKategorija(
				izmeniProizvod.getKategorija()
		);



		validiraj(postojeciProizvod);


		return proizvodRepository.save(postojeciProizvod);

	}





	// dopuna lagera
	public Proizvod dopuniLager(Long id, int kolicina) {


		Proizvod proizvod = findById(id);


		if(kolicina <= 0) {

			throw new RuntimeException(
					"Kolicina mora biti veca od 0"
			);

		}


		proizvod.setKolicinaNaLageru(
				proizvod.getKolicinaNaLageru() + kolicina
		);


		return proizvodRepository.save(proizvod);

	}





	// kupovina proizvoda
	public Proizvod kupiProizvod(Long id, int kolicina) {


		Proizvod proizvod = findById(id);



		if(kolicina <= 0) {

			throw new RuntimeException(
					"Kolicina mora biti veca od 0"
			);

		}



		if(proizvod.getKolicinaNaLageru() < kolicina) {

			throw new RuntimeException(
					"Nema dovoljno proizvoda na lageru"
			);

		}



		proizvod.setKolicinaNaLageru(
				proizvod.getKolicinaNaLageru() - kolicina
		);



		return proizvodRepository.save(proizvod);

	}





	// brisanje proizvoda
	public void delete(Long id) {


		Proizvod proizvod = findById(id);


		proizvodRepository.delete(proizvod);

	}


}