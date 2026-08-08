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

import rs.singidunum.projekat.model.Korisnik;
import rs.singidunum.projekat.service.KorisnikService;


@RestController
@RequestMapping("/korisnici")
public class KorisnikController {


	private final KorisnikService korisnikService;


	public KorisnikController(KorisnikService korisnikService) {
		this.korisnikService = korisnikService;
	}



	// prikaz svih korisnika
	@GetMapping
	public List<Korisnik> getAllKorisnici() {

		return korisnikService.findAll();
	}



	// prikaz korisnika po id
	@GetMapping("/{id}")
	public Korisnik getKorisnikById(
			@PathVariable Long id) {


		return korisnikService.findById(id);
	}



	// kreiranje korisnika
	@PostMapping
	public Korisnik createKorisnik(
			@RequestBody Korisnik korisnik) {


		return korisnikService.save(korisnik);
	}



	// izmena korisnika
	@PutMapping("/{id}")
	public Korisnik updateKorisnik(
			@PathVariable Long id,
			@RequestBody Korisnik korisnik) {


		return korisnikService.update(id, korisnik);
	}



	// brisanje korisnika
	@DeleteMapping("/{id}")
	public void deleteKorisnik(
			@PathVariable Long id) {


		korisnikService.delete(id);
	}



	// pretraga korisnika po imenu
	@GetMapping("/pretraga")
	public List<Korisnik> pretraga(
			@RequestParam String ime) {


		return korisnikService.pretraga(ime);
	}


}