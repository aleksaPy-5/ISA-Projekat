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



	// prikaz svih kategorija
	public List<Kategorija> findAll() {

		return kategorijaRepository.findAll();

	}




	// pronalazak kategorije po id-u
	public Kategorija findById(Long id) {

		return kategorijaRepository.findById(id)
				.orElseThrow(() -> 
				new RuntimeException("Nepostojeca kategorija"));

	}




	// pretraga kategorija po nazivu
	public List<Kategorija> pretraga(String naziv) {

		return kategorijaRepository
				.findByNazivContainingIgnoreCase(naziv);

	}




	// sortiranje kategorija po nazivu
	public List<Kategorija> sortirajPoNazivu() {

		return kategorijaRepository.findAll()
				.stream()
				.sorted((k1, k2) -> 
						k1.getNaziv()
						.compareToIgnoreCase(k2.getNaziv()))
				.toList();

	}





	// validacija kategorije
	private void validiraj(Kategorija kategorija) {


		if(kategorija.getNaziv() == null ||
				kategorija.getNaziv().trim().isEmpty()) {


			throw new RuntimeException(
					"Naziv kategorije je obavezan");

		}

	}




	// dodavanje kategorije
	public Kategorija save(Kategorija kategorija) {


		validiraj(kategorija);



		if(kategorijaRepository.existsByNazivIgnoreCase(
				kategorija.getNaziv())) {


			throw new RuntimeException(
					"Kategorija vec postoji!");

		}



		return kategorijaRepository.save(kategorija);

	}






	// izmena kategorije
	public Kategorija update(Long id, Kategorija izmeniKategoriju) {


		Kategorija postojecaKategorija = findById(id);


		validiraj(izmeniKategoriju);



		if(!postojecaKategorija.getNaziv()
				.equalsIgnoreCase(
						izmeniKategoriju.getNaziv())
				
				&& kategorijaRepository
				.existsByNazivIgnoreCase(
						izmeniKategoriju.getNaziv())) {



			throw new RuntimeException(
					"Kategorija vec postoji!");

		}




		postojecaKategorija.setNaziv(
				izmeniKategoriju.getNaziv());



		return kategorijaRepository.save(
				postojecaKategorija);

	}






	// brisanje kategorije
	public void delete(Long id) {


		Kategorija kategorija = findById(id);



		if(kategorija.getProizvodi() != null &&
				!kategorija.getProizvodi().isEmpty()) {



			throw new RuntimeException(
					"Ne mozete obrisati kategoriju koja ima proizvode");

		}



		kategorijaRepository.delete(kategorija);

	}


}