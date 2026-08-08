package rs.singidunum.projekat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.singidunum.projekat.model.Porudzbina;
import rs.singidunum.projekat.model.Korisnik;
import rs.singidunum.projekat.service.PorudzbinaService;


@RestController
@RequestMapping("/porudzbine")
public class PorudzbinaController {


	private final PorudzbinaService porudzbinaService;


	public PorudzbinaController(PorudzbinaService porudzbinaService) {
		this.porudzbinaService = porudzbinaService;
	}



	// sve porudzbine
	@GetMapping
	public List<Porudzbina> getAllPorudzbine() {

		return porudzbinaService.findAll();
	}



	// porudzbina po id
	@GetMapping("/{id}")
	public Porudzbina getPorudzbinaById(
			@PathVariable Long id) {


		return porudzbinaService.findById(id);
	}



	// porudzbine jednog korisnika
	@GetMapping("/korisnik/{korisnikId}")
	public List<Porudzbina> getByKorisnik(
			@PathVariable Long korisnikId) {


		return porudzbinaService.findByKorisnikId(korisnikId);
	}



	// kreiranje porudzbine
	@PostMapping
	public Porudzbina createPorudzbina(
			@RequestBody Porudzbina porudzbina,
			@RequestParam Long korisnikId) {


		Korisnik korisnik = new Korisnik();
		korisnik.setId(korisnikId);


		return porudzbinaService.napraviPorudzbinu(
				porudzbina,
				korisnik
		);
	}



	// kupovina jednog proizvoda
	@PostMapping("/kupi")
	public Porudzbina kupi(
			@RequestParam Long proizvodId,
			@RequestParam Long korisnikId,
			@RequestParam int kolicina) {


		return porudzbinaService.kupi(
				proizvodId,
				korisnikId,
				kolicina
		);
	}



	// otkazivanje porudzbine
	@PutMapping("/{id}/otkazi")
	public Porudzbina otkazi(
			@PathVariable Long id) {


		return porudzbinaService.otkaziPorudzbinu(id);
	}



	// promena statusa
	@PutMapping("/{id}")
	public Porudzbina update(
			@PathVariable Long id,
			@RequestBody Porudzbina porudzbina) {


		return porudzbinaService.update(
				id,
				porudzbina
		);
	}



	// brisanje
	@DeleteMapping("/{id}")
	public void delete(
			@PathVariable Long id) {


		porudzbinaService.delete(id);
	}

}