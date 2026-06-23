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
	
	@GetMapping
	public List<Korisnik> findAll() {
		return korisnikService.findAll();	
	}
	
	@GetMapping("/{id}")
	public Korisnik findById(@PathVariable Long id) {
		return korisnikService.findById(id);
	}
	
	@PostMapping
	public Korisnik save(@RequestBody Korisnik korisnik) {
		return korisnikService.save(korisnik);
	}
	
	@PutMapping("/{id}")
	public Korisnik update(@PathVariable Long id, @RequestBody Korisnik korisnik) {
		return korisnikService.update(id, korisnik);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		korisnikService.delete(id);
	}
	
	@GetMapping("/pretraga-ime")
	public List<Korisnik> pretragaIme (@RequestParam String ime) {
		return korisnikService.pretraga(ime);
	}
	
	@PostMapping("/login")
	public Korisnik login(@RequestParam String email, @RequestParam String lozinka) {
		return korisnikService.login(email, lozinka);
	}
}
